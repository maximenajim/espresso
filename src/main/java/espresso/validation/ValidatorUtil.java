package espresso.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidatorUtil {

    private ValidatorUtil() {}

    public static <Entity extends NamedEntity, VC extends NamedEntityValidatorContext<Entity>>
        Set<Entity> validateUniqueNames(List<Entity> newEntityList, VC vc){

        Set<Entity> duplicateEntities = new HashSet<Entity>();
        // check name uniqueness with other entity names in db
        for (Entity newEntity : newEntityList) {

            String name = newEntity.getName();
            // add error if the name already exists in db for a different entity
            if (name != null && vc.getExistingEntityByName(name) != null
                    && !vc.getExistingEntityByName(name).getId().equals(newEntity.getId())) {
                duplicateEntities.add(newEntity);
            }
        }

        // check name uniqueness with in input entities being added/updated within same batch
        Set<String> newEntityNames = new HashSet<String>();
        for (Entity newEntity : newEntityList) {

            String name = newEntity.getName();

            // add error to the dup entity names in the same batch
            if (name != null && newEntityNames.contains(name)) {
                duplicateEntities.add(newEntity);
            }

            newEntityNames.add(name);
        }
        return duplicateEntities;
    }
}
