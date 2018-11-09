/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * Picture objext.
     */
    Picture picture;
    /**
     * Color array.
     */
    int[][] color;
    /**
     * Energy array.
     */
    Double[][] energy;
    /**
     * X and Y integer.
     */
    int x;
    int y;
    /**
     * Constructs the object.
     * create a seam carver object based on the given picture.
     * 
     *
     * @param      picture1  The picture 1
     */
    public SeamCarver(final Picture picture1) {
        x = picture1.height();
        y = picture1.width();
        this.picture = picture1;
        color = new int[picture1.width()][picture1.height()];
        energy = new Double[picture1.width()][picture1.height()];
    }
    /**
     * current picture. 
     *
     * @return     { description_of_the_return_value }
     */
    public Picture picture() {
        return null;
    }
    /**
     * width of current picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return picture.width();
    }

    /**
     * height of current picture. 
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return picture.height();
    }

    /**
     * energy of pixel at column x and row y. 
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double energy(final int x, final int y) {
        if (x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1) {
            //energy[x][y] = Double.parseDouble(1000);
            return 1000;
        }

        double deltaX = 0;
        double topRedx = picture.get(x - 1, y).getRed();
        double topGreenx = picture.get(x - 1, y).getGreen();
        double topBluex = picture.get(x - 1, y).getBlue();
        double bottomRedx = picture.get(x + 1, y).getRed();
        double bottomGreenx = picture.get(x + 1, y).getGreen();
        double bottomBluex = picture.get(x + 1, y).getBlue();
        deltaX = Math.pow(topRedx - bottomRedx , 2) + Math.pow(topGreenx - bottomGreenx, 2)
        + Math.pow(topBluex - bottomBluex, 2);
        double deltaY = 0;
        double rightRedy = picture.get(x, y - 1).getRed();
        double rightGreeny = picture.get(x, y- 1).getGreen();
        double rightBluey = picture.get(x, y - 1).getBlue();
        double leftRedy = picture.get(x, y + 1).getRed();
        double leftGreeny = picture.get(x, y + 1).getGreen();
        double leftBluey = picture.get(x, y + 1).getBlue();
        deltaY = Math.pow(rightRedy - leftRedy , 2) + Math.pow(rightGreeny - leftGreeny, 2)
        + Math.pow(rightBluey - leftBluey, 2);
        double energySum = deltaX + deltaY;
        double energy = Math.sqrt(energySum);
        //energy[x][y] = energy;
        return energy;
    }

    public int[] findHorizontalSeam() {
        double[][] pathSum =new double[width()][height()];
        int[][] parent = new int[width()][height()];
        for(int i =0 ;i<height();i++){
            pathSum[0][i] = 1000;
            parent[0][i]=i;
        }
        for(int x= 1; x <width();x++){
            for(int y =0 ;y<height();y++){
                double tempSum =  pathSum[x-1][y];
                parent[x][y] = y;
                if(y>0 && pathSum[x-1][y-1] <tempSum){
                    tempSum = pathSum[x-1][y-1];
                    parent[x][y] = y-1;
                }
                if(y<height()-1 && pathSum[x-1][y+1] < tempSum){
                    tempSum = pathSum[x-1][y+1];
                    parent[x][y] = y+1;
                }
                pathSum[x][y]=tempSum+energy(x,y);
            }
        }
        int minIndex=0;

        for(int i = 1; i<height();i++){
            if(pathSum[width()-1][i] < pathSum[width()-1][minIndex]){
                minIndex = i;
            }
        }

        int res [] = new int[width()];
        res[width()-1] = minIndex;
        for (int h = width()-2;h>=0 ;h-- ) {
            res[h]= parent[h+1][minIndex];
            minIndex=parent[h+1][minIndex];
        }
        return res;

        

        
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] pathSum =new double[width()][height()];
        int[][] parent = new int[width()][height()];
        for(int i =0 ;i<width();i++){
            pathSum[i][0] = 1000;
            parent[i][0]=i;
        }
        for(int y = 1; y <height();y++){
            for(int x =0 ;x<width();x++){
                double tempSum =  pathSum[x][y-1];
                parent[x][y] = x;
                if(x>0 && pathSum[x-1][y-1] <tempSum){
                    tempSum = pathSum[x-1][y-1];
                    parent[x][y] = x-1;
                }
                if(x<width()-1 && pathSum[x+1][y-1] <tempSum){
                    tempSum = pathSum[x+1][y-1];
                    parent[x][y] = x+1;
                }
                pathSum[x][y]=tempSum+energy(x,y);
            }
        }
        int minIndex=0;

        for(int i = 1; i<width();i++){
            if(pathSum[i][height()-1] < pathSum[minIndex][height()-1]){
                minIndex = i;
            }
        }

        int res [] = new int[height()];
        res[height()-1] = minIndex;
        for (int h = height()-2;h>=0 ;h-- ) {
            //res[h] = parent[res[h+1]][h]; 
            res[h]= parent[minIndex][h+1];
            minIndex=parent[minIndex][h+1];
        }
        return res;

        //return new int[0];
    }

    /**
     * Removes a horizontal seam.
     * remove horizontal seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {

    }

    /**
     * Removes a vertical seam.
     * remove vertical seam from current picture. 
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {

    }
}