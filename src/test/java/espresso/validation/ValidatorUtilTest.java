package espresso.validation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ValidatorUtilTest {

    @Test
    public void test_happy_path(){
        List<NamedEntity> newEntities = new ArrayList<NamedEntity>();
        newEntities.add(mock(NamedEntity.class));

        List<NamedEntity> existingEntities = new ArrayList<NamedEntity>();
        existingEntities.add(mock(NamedEntity.class));
        NamedEntityValidatorContext vc = NamedEntityValidatorContext.load(existingEntities);
        assertTrue(ValidatorUtil.validateUniqueNames(newEntities,vc).isEmpty());
    }

    @Test
    public void test_duplicate_names_new(){
        List<NamedEntity> newEntities = new ArrayList<NamedEntity>();
        NamedEntity namedEntity1 = mock(NamedEntity.class);
        when(namedEntity1.getId()).thenReturn(1l);
        when(namedEntity1.getName()).thenReturn("Duplicate");
        newEntities.add(namedEntity1);
        NamedEntity namedEntity2 = mock(NamedEntity.class);
        when(namedEntity2.getId()).thenReturn(2l);
        when(namedEntity2.getName()).thenReturn("Duplicate");
        newEntities.add(namedEntity2);

        List<NamedEntity> existingEntities = new ArrayList<NamedEntity>();
        existingEntities.add(mock(NamedEntity.class));
        NamedEntityValidatorContext vc = NamedEntityValidatorContext.load(existingEntities);
        assertTrue(ValidatorUtil.validateUniqueNames(newEntities,vc).size() == 1);
    }

    @Test
    public void test_duplicate_names_existing(){
        List<NamedEntity> newEntities = new ArrayList<NamedEntity>();
        NamedEntity namedEntity1 = mock(NamedEntity.class);
        when(namedEntity1.getId()).thenReturn(1l);
        when(namedEntity1.getName()).thenReturn("Duplicate");
        newEntities.add(namedEntity1);

        List<NamedEntity> existingEntities = new ArrayList<NamedEntity>();
        existingEntities.add(mock(NamedEntity.class));
        NamedEntity namedEntity2 = mock(NamedEntity.class);
        when(namedEntity2.getId()).thenReturn(2l);
        when(namedEntity2.getName()).thenReturn("Duplicate");
        existingEntities.add(namedEntity2);
        NamedEntityValidatorContext vc = NamedEntityValidatorContext.load(existingEntities);
        assertTrue(ValidatorUtil.validateUniqueNames(newEntities,vc).size() == 1);
    }
}
