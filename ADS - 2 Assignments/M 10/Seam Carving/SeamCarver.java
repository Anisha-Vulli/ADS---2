
public class SeamCarver {
    Picture picture;
    int[][] color;
    Double[][] energy;
    int x;
    int y;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture1) {
        x = picture1.height();
        y = picture1.width();
        this.picture = picture1;
        color = new int[picture1.width()][picture1.height()];
        energy = new Double[picture1.width()][picture1.height()];

        // for (int i = 0; i < x; i++) {
        //     for (int j = 0; j < y; j++) {
        //         color[i][j] = picture1.get(i, j).getRGB();
        //     }
        // }

        // for (int i = 0; i < x; i++) {
        //     for (int j = 0; j < y; j++) {
        //         energy[i][j] = energy(i, j);
        //     }
        // }
    }
    // current picture
    public Picture picture() {
        return null;
    }
    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1) {
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
        return energy;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}