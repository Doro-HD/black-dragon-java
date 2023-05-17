package dk.acto.blackdragon.implementations;

import io.vavr.collection.List;
import java.util.Scanner;
import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.service.ModelFactory;

public class ModelFactoryImpl implements ModelFactory<Model> {
    
    public List<Model> parse(String string) {
        final Scanner scanner = new Scanner(string);
        scanner.useDelimiter("\n");

        // the first line are the columns, they will be skipped
        scanner.nextLine();

        List<Model> models = List.empty();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            //some valueOf methods throws exceptions if there is whitespace in the string
            //removing whitespace with replace using a regex expression
            line = line.replace("\s", "");
            
            // index
            // 0: id, 1: weight, 2: cost, 3: inventory
            String[] values = line.split(",");
            
            long id = Long.valueOf(values[0]);
            double weight = Double.valueOf(values[1]);
            int cost = Integer.valueOf(values[2]);
            long inventory = Long.valueOf(values[3]); 
            
            Model model = Model.builder()
            .id(id)
            .weight(weight)
            .cost(cost)
            .inventory(inventory)
            .build();

            models = models.append(model);
        }

        return models;
    }
}
