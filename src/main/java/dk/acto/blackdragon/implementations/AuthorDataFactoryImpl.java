package dk.acto.blackdragon.implementations;

import java.net.URL;
import java.net.MalformedURLException;
import dk.acto.blackdragon.service.AuthorDataFactory;
import dk.acto.blackdragon.model.AuthorData;

public class AuthorDataFactoryImpl implements AuthorDataFactory {
    
    public AuthorData create() {
        final String name = "David";
        final URL linkedInProfile;
        final URL solutionRepository;
        
        try {
            linkedInProfile = new URL("https://www.linkedin.com/in/david-borja-jakobsen-01a655239/");
            solutionRepository = new URL("https://github.com/Doro-HD/black-dragon-java");
        } catch (MalformedURLException e) {
            return null;
        }

        final AuthorData authorData = AuthorData.builder()
            .name(name)
            .linkedInProfile(linkedInProfile)
            .solutionRepository(solutionRepository)
            .build();

        return authorData;
    }
}
