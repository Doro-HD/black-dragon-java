package dk.acto.blackdragon.implementations;

import java.math.BigInteger;
import java.math.BigDecimal;
import io.vavr.collection.List;
import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.model.Stats;
import dk.acto.blackdragon.service.ModelTransformer;

public class ModelTransformerImpl implements ModelTransformer<Model, Stats> {
    
    public Stats transform(List<Model> models) {
        final int evenIdsInt = models.count(model -> model.getId() % 2 == 0);
        final BigInteger evenIds = BigInteger.valueOf(evenIdsInt);

        final int oddIdsInt = models.size() - evenIdsInt;
        final BigInteger oddIds = BigInteger.valueOf(oddIdsInt);

        //the cost is in cents and the test expects the amount in dollars
        //dividing by 100 will convert the cost to dollars
        final int meanCostDouble = models
            .map(model -> model.getCost())
            .reduce((x, y) -> x + y) / models.size() / 100;
        final BigDecimal meanCost = BigDecimal.valueOf(meanCostDouble);

        final double weightedInventoryDouble = models
            .map(model -> model.getWeight() * model.getInventory())
            .reduce((x, y) -> x + y);
        BigDecimal weightedInventory = BigDecimal.valueOf(weightedInventoryDouble);

        //will set the value to be a 2 digits decimal number
        weightedInventory = weightedInventory.setScale(2, BigDecimal.ROUND_DOWN);

        long totalInventoryLong = models
            .map(model -> model.getInventory())
            .reduce((x, y) -> x + y);
        BigInteger totalInventory = BigInteger.valueOf(totalInventoryLong);
        
        return Stats.builder()
            .evenIds(evenIds)
            .oddIds(oddIds)
            .meanCost(meanCost)
            .weightedInventory(weightedInventory)
            .totalInventory(totalInventory)
            .build();
    }
}
