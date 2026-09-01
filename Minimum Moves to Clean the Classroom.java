import java.util.*;

class Solution {
    // Helper class to store the state of the BFS
    private static class State {
        int r, c, energy, mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();
        
        // Step 1: Locate the starting point and all litter instances
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int totalLitters = litters.size();
        int targetMask = (1 << totalLitters) - 1;
        
        // Map to quickly fetch the bit-index of any litter cell
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) Arrays.fill(row, -1);
        for (int i = 0; i < totalLitters; i++) {
            int[] pos = litters.get(i);
            litterIdx[pos[0]][pos[1]] = i;
        }
        
        // Step 2: Initialize BFS structures
        Queue<State> queue = new LinkedList<>();
        // visited[r][c][energy][mask]
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << totalLitters];
        
        // Initial state check if start cell itself happens to contain a litter
        int initialMask = 0;
        if (litterIdx[startR][startC] != -1) {
            initialMask |= (1 << litterIdx[startR][startC]);
        }
        
        queue.offer(new State(startR, startC, energy, initialMask));
        visited[startR][startC][energy][initialMask] = true;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Step 3: Run standard layered BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                State curr = queue.poll();
                
                // If all litter items are collected, return the minimum moves
                if (curr.mask == targetMask) {
                    return moves;
                }
                
                // If energy runs out, we cannot make any further moves from here
                if (curr.energy == 0) {
                    continue;
                }
                
                for (int[] d : dirs) {
                    int nr = curr.r + d[0];
                    int nc = curr.c + d[1];
                    
                    // Boundary & Obstacle checks
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    char nextCell = classroom[nr].charAt(nc);
                    int nextEnergy = curr.energy - 1;
                    int nextMask = curr.mask;
                    
                    // Recharge handling
                    if (nextCell == 'R') {
                        nextEnergy = energy;
                    }
                    
                    // Litter collection handling
                    if (litterIdx[nr][nc] != -1) {
                        nextMask |= (1 << litterIdx[nr][nc]);
                    }
                    
                    // Push to queue if this composite state hasn't been visited
                    if (!visited[nr][nc][nextEnergy][nextMask]) {
                        visited[nr][nc][nextEnergy][nextMask] = true;
                        queue.offer(new State(nr, nc, nextEnergy, nextMask));
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
