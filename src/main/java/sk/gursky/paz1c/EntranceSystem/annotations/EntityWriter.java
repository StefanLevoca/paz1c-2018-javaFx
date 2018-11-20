package sk.gursky.paz1c.EntranceSystem.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import sk.gursky.paz1c.EntranceSystem.persistent.DaoFactory;

public class EntityWriter {
    
//  private static final Logger logger = LoggerFactory.getLogger(EntityWriter.class);
  
  public static void main(String[] args) {
      
      Class<DaoFactory> daoFactoryClass = DaoFactory.class;
      
      for (Method method : daoFactoryClass.getDeclaredMethods()) {
          if (method.isAnnotationPresent(DaoGetter.class)) {
              try {
                  DaoGetter annotation = method.getAnnotation(DaoGetter.class);
                  System.out.println("Entity: " + annotation.description());
                  Object dao = method.invoke(DaoFactory.INSTANCE);
                  writeEntities(dao);
              } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                 //logger.warn("neviem zavolat metodu " + method.getName() , ex);
                  System.err.println("neviem zavolat metodu " + method.getName());
              }
          }
      }
  }
  
  private static void writeEntities(Object dao) {
      Class daoClass = dao.getClass();
      for (Method method : daoClass.getDeclaredMethods()) {
          if (method.isAnnotationPresent(EntityGetter.class)) {
              try {
                  List entities = (List) method.invoke(dao);
                  for (Object entity : entities) {
                      System.out.println(entity);
                  }
              } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                 logger.warn("neviem zavolat metodu " + method.getName() , ex);
                  System.err.println("neviem zavolat metodu " + method.getName());
                  ex.printStackTrace();
              }
          }
      }
  }
}

