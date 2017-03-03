import java.util.*;
import org.sql2o;

public class Endangered extends Aniamls{

private String health;
private String age;

public static final String ENDANGERED_TYPE;
public static final String [] HEALTHS = new String [] {"healthy","ill","okay"};
public static final String [] AGES = New String []{"newborns","young","adult"};

public Endangered(String name, String health, String age,String type){
	this.name = name;
	this.health= health;
	this.age = age;
	this.type= ENDANGERED_TYPE;
}

public String getName(){

}

public String getAge(){
	return age;
}

public String getHealth(){
	return health;
}

public String getType(){
	return type;
}

  @Override
  public boolean equals(Object otherEndangered){
    if (!(otherEndangered instanceof Endangered)) {
      return false;
    } else {
      Endangered newEndangered = (Endangered) otherEndangered;
      return this.getName().equals(newEndangered.getName())&&
       this.getAge().equals(newEndangered.getAge())&&
       this.getHealth().equals(newEndangered.getHealth())&&
       this.getType().equals(newEndangered.getType())&&
       this.getId()==(newEndangered.getId());

    }
  }

@Override
public static List<Endangered> all(){
	String sql = "SELECT * FROM animals WHERE type='type';";
	try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .exexcuteAndFetch(Endangered.class);
	}
}

@Override
public static Endangered find(int id){
try(Connection con = DB.sql2o.open()){
   String sql = "SELECT * From animals WHERE id=:id";
   Endangered endangered = con.createQuery(sql)
   .addParameter("id",id)
   .throwOnMappingFailure(false)
   .exexcuteAndFetchFirdt(Endangered.class);
}
}

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name,type,age,health) VALUES (:name,:type,:age,:health)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("health",this.health)
        .addParameter("age",this.age)
        .addParameter("name", this.name)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }



}