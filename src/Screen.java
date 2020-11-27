public class Screen {

    private int width, heigth;
    public int pixels[];

    public Screen (int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        pixels = new int[width * heigth];
    }

    public void render () {
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0x0F0F20;
            }
        }
    }

}
