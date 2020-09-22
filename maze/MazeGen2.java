
package maze;
import java.util.LinkedList;
import java.util.Random;

public class MazeGen2 {
    public static final String PASSAGE_CHAR = "  ";
    public static final String WALL_CHAR = "\u2588\u2588";
    public static final boolean WALL = false;
    public static final boolean PASSAGE = !WALL;

    private final boolean map[][];
    private final int width;
    private final int height;

    public MazeGen2(final int width , final int height) {
        this.width = width ;
        this.height = height ;
        this.map = new boolean[this.width][this.height];

        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        frontiers.add(new int[]{x, y, x, y});

        while (!frontiers.isEmpty()) {
            final int[] f = frontiers.remove(random.nextInt(frontiers.size()));
            x = f[2];
            y = f[3];
            if (map[x][y] == WALL) {
                map[f[0]][f[1]] = map[x][y] = PASSAGE;
                if (x >= 2 && map[x - 2][y] == WALL)
                    frontiers.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && map[x][y - 2] == WALL)
                    frontiers.add(new int[]{x, y - 1, x, y - 2});
                if (x < width - 2 && map[x + 2][y] == WALL)
                    frontiers.add(new int[]{x + 1, y, x + 2, y});
                if (y < height - 2 && map[x][y + 2] == WALL)
                    frontiers.add(new int[]{x, y + 1, x, y + 2});
            }
        }
    }

    @Override
    public String toString() {
        final StringBuffer b = new StringBuffer();
//        for (int x = 0; x < width + 2; x++)
//            b.append(WALL_CHAR);
//        b.append('\n');
        for (int y = 0; y < width; y++) {
            b.append(WALL_CHAR);
            for (int x = 0; x < height; x++)
                b.append(map[y][x] == WALL ? WALL_CHAR : PASSAGE_CHAR);
            b.append(WALL_CHAR);
            b.append('\n');
        }
//        for (int x = 0; x < width + 2; x++)
//            b.append(WALL_CHAR);
//        b.append('\n');
        return b.toString();
    }

    public void printgrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i][j] == true) {
                    System.out.print("  ");
                } else {
                    System.out.print("\u2588\u2588");
                }
            }
            System.out.println();
        }
    }
}