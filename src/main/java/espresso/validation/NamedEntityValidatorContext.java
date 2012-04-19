package espresso.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedEntityValidatorContext<Entity extends NamedEntity> {

    private List<Entity> existingEntities;
    private Map<Long,Entity> existingEntitiesIdMap;
    private Map<String,Entity> existingEntitiesNameMap;

    private NamedEntityValidatorContext(List<Entity> existingEntitiesList) {
        existingEntities = existingEntitiesList;
        existingEntitiesIdMap = new HashMap<Long,Entity>();
        existingEntitiesNameMap = new HashMap<String,Entity>();
    }

    public static <Entity extends NamedEntity> NamedEntityValidatorContext load(List<Entity> existingEntities) {
        NamedEntityValidatorContext<Entity> validatorContext = new NamedEntityValidatorContext<Entity>(existingEntities);
        for(Entity entity : existingEntities){
            validatorContext.existingEntitiesIdMap.put(entity.getId(), entity);
            validatorContext.existingEntitiesNameMap.put(entity.getName(), entity);
        }
        return validatorContext;

    }

    public Entity getExistingEntityById(Long id){
        Entity entity = null;
        if(existingEntitiesIdMap != null){
            entity = existingEntitiesIdMap.get(id);
        }
        return entity;
    }

    public Entity getExistingEntityByName(String name){
        Entity entity = null;
        if(existingEntitiesNameMap != null){
            entity = existingEntitiesNameMap.get(name);
        }
        return entity;
    }

    public List<Entity> getExistingEntities() {
        return existingEntities;
    }
}
