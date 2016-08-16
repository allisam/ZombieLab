/*
*@ samson
*/
public class ZombieAttack {
    private ZombieCell[][] world;
    private int width;
    private int height;
    private int lifetime;
    private PicGrid grid;

    public ZombieAttack (int w, int h, int zombies, int people, int lt) {
        grid = new PicGrid(h,w);        // create a new PicGrid object
        world = new ZombieCell[h][w];   // create the two-dimensional array
        lifetime = lt;                  // initialize lifetime
        width = w;                      // initialize width
        height = h;                     // initialize height
        int r, c;                       // for iterating over the array
        
        r = 0;  // start in the first row
        while (r < height) {    // for each row in the array
            c = 0;  // start in the first column
            while (c < width) { // for each column in the array
                // create a new ZombieCell
                world[r][c] = new ZombieCell(lifetime);
                c += 1; // move to next column
            }
            r += 1; // move to next row
        }

        // populate the world with zombies
        while (zombies > 0) {
            r = (int) (Math.random() * height);
            c = (int) (Math.random() * width);
            world[r][c] = new ZombieCell(lifetime, true);
            zombies -= 1;
        }

        // populate the world with people
        while (people > 0) {
            r = (int) (Math.random() * height);
            c = (int) (Math.random() * width);
            world[r][c] = new ZombieCell(lifetime, false);
            people -= 1;
        }
    }

    private void paint() {
       int ro = 0;  // start in the first row
        while (ro < height) {    // for each row in the array
            int col = 0;  // start in the first column
            while (col < width) { // for each column in the array
                // create a new ZombieCell
     if(world[ro][col].isInhabited()==true){
        if(world[ro][col].isInfected()==true){
          grid.setGreen(ro,col);
          col+=1;
        }
        else{
          grid.setBlack(ro,col);
          col+=1;
        }
      }
      else{
        grid.setWhite(ro,col);
        col+=1;
    }
            }
            ro += 1; // move to next row
            
        }
    
    }
    public void simulate(int days) {
        grid.show();
        grid.draw();
        int count=0;
        while(count<days+1){
          step();
          paint();
          grid.draw();
          count+=1;
        }
    }


    private void step() {
        int x = 0;  // start in the first row
        while (x < height) {    // for each row in the array
            int y = 0;  // start in the first column
            while (y < width) { // for each column in the array
              if(world[x][y].isInfected()==true){
                cell(x,y);
              }
                y += 1; // move to next column
            }
            x += 1; // move to next row
        }
    }
    

    private void cell(int r, int c) {
        int newr = 0;   // the row this zombie will move to
        int newc = 0;   // the column this zombie will move to
        // get direction from zombie
        int direction = world[r][c].move();

        // if it is west and we are not in the 0 column
        if (direction == 1 && c > 0) {
            // move west
            newr = r;
            newc = c-1;
        }
        // if it is north and we are not in the 0 row
        else if (direction == 2 && r > 0) {
            // move north
            newr = r-1;
            newc = c;
        }
        // if it is east and we are not in the easternmost column
        else if (direction == 3 && c < width-1) {
            // move east
            newr = r;
            newc = c+1;
        }
        // if it south and we are not in the sourthernmost row
        else if (direction == 4 && r < height-1) {
            // move south
            newr = r+1;
            newc = c;
        }
        
        // if the zombie wanted to move
        if (direction != 0) {
            // if the existing cell is inhabited
            if (world[newr][newc].isInhabited()) {
                // infect it
                world[newr][newc].infect();
            }
            else {
                // otherwise, swap that cell with this cell
                ZombieCell tmp = world[newr][newc];
                world[newr][newc] = world[r][c];
                world[r][c] = tmp;
            }
        }
    }
}
