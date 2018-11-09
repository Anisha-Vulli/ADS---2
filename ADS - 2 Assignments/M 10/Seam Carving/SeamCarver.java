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
    public SeamCarver(Picture picture1) {
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
    public double energy(int x, int y) {
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

    /**
     * sequence of indices for horizontal seam. 
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    /**
     * sequence of indices for vertical seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        return new int[0];
    }

    /**
     * Removes a horizontal seam.
     * remove horizontal seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(int[] seam) {

    }

    /**
     * Removes a vertical seam.
     * remove vertical seam from current picture. 
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(int[] seam) {

    }
}