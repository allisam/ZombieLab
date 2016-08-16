/*
*@ samson
*/
public class ZombieCell {
    private boolean inhabited;
    private boolean infected;
    private int daysInfected;
    private int lifetime;

    public ZombieCell (int lt) {
        lifetime=lt;
        infected=false;
        inhabited=false;
                
    }

    public ZombieCell (int lt, boolean infct) {
        lifetime=lt;
        infected=infct;
        inhabited=true;
    
    }

    public boolean isInfected() {
      if(infected==true){
          return true;
      }
      else{
        return false;
      }
    }

    public boolean isInhabited() {
      if(inhabited==true){
        return true;
      }
      else{
        return false;
      }
    }

    public void infect() {
        infected=true;
        daysInfected=0;
          
    }

    public int move() {
      if(infected=true){
        daysInfected+=1;
        if(daysInfected>lifetime){
          infected=false;
          inhabited=false;
          daysInfected=0;
          return 0;
        }
        else{
          int number=(int)(Math.random()*4)+1;
          return number;
                            }
      }
        else{
          return 0;
}
}
          
}

