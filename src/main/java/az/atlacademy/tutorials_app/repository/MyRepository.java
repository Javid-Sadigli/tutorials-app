package az.atlacademy.tutorials_app.repository;

import java.util.List;

public interface MyRepository <ObjectType, IdType>  
{
    public ObjectType save(ObjectType object);
    public ObjectType findById(IdType id);
    public List<ObjectType> findAll(); 
    public void deleteById(IdType id);
    public void deleteAll();
}
