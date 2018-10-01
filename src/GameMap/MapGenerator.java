package GameMap;

import java.util.Random;

public class MapGenerator {

    public static MapCell[][] generateMap(
            int fillPercent,
            int smoothingCycles,
            int smoothingParameter,
            boolean makeWalls,
            int width,
            int height,
            long seed)
    {
        MapCell[][] map = new MapCell[width][height];
        generateNoise(map, fillPercent, seed, makeWalls);
        for (int i = 0; i < smoothingCycles; i++)
            smooth(map, smoothingParameter);

        return map;
    }

    private static void smooth(MapCell[][] map, int smoothingParameter) {
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                int walls = countSurroundingWalls(map, x, y);
                if (walls < smoothingParameter){
                    map[x][y] = MapCell.SPACE;
                    continue;
                }
                if (walls > smoothingParameter){
                    map[x][y] = MapCell.WALL;
                    continue;
                }
            }
        }
    }

    private static int countSurroundingWalls(MapCell[][] map, int x, int y) {
        int counter = 0;
        for (int xc = x - 1; xc <= x + 1; xc++) {
            for (int yc = y - 1; yc <= y + 1; yc++) {
                if (xc == x && yc == y){
                    continue;
                }
                if (xc < 0 || yc < 0 || xc >= map[0].length || yc >= map.length){
                    counter++;
                    continue;
                }
                if (map[xc][yc] == MapCell.WALL)
                    counter++;
            }
        }
        return counter;
    }

    private static void generateNoise(MapCell[][] map, int fillPercent, long seed, boolean makeWalls) {
        Random randomGen = new Random(seed);
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (makeWalls){
                    if (x == 0 || y == 0 || x == map[0].length - 1 || y == map.length - 1){
                        map[x][y] = MapCell.WALL;
                        continue;
                    }
                }
                map[x][y] = (randomGen.nextInt(100) < fillPercent)
                        ? MapCell.WALL
                        : MapCell.SPACE;
            }
        }
    }

}
