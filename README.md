# Contest 2
*Legend:*
- ⏳ - in progress
- ✅ - done
- ❌ - undone
## TASKS:
### - Task D [⏳]
In classic video games, you might encounter a scenario where a hero jumps across platforms suspended in the air, needing to travel from one side of the screen to the other. 
In one version of this game, when the hero jumps from one platform to an adjacent one, he uses $|y_2 - y_1|$ energy, where $y_1$ and $y_2$ are the heights of these platforms. 
Additionally, there's a special move that allows the hero to skip a platform, costing $3·|y_3 - y_1|$ energy. Another version of the game changes the energy calculation by using 
squares instead of absolute values: $(y_2 - y_1)^2$ for a jump to a neighboring platform and $3·(y_3 - y_1)^2$ for skipping one. The heights of the platforms are given in 
sequence from the leftmost to the rightmost edge. Your task is to determine the minimum energy required to move from the 1st platform to the n-th (last) platform for each 
version of the game.

#### Input
First, you are given the number of platforms N (2 ≤ N ≤ 50000), followed by N integers ranging from -2000 to +2000, representing the heights of these platforms.

#### Output
Output a single line with two space-separated integers — the minimum energy required for the first version of the game and for the second version.

##### Note: 
In the first version, it's more efficient to jump consecutively across platforms 0 → 20 → 11, with a total energy cost of $20 + 9 = 29$. In the second version, 
it's better to jump directly from 0 to 11 ($3·11^2 = 363$) rather than jumping across all consecutive platforms ($20^2 + 9^2 = 481$).

#### Examples
##### Input #1
3 0 20 11
##### Answer #1
29 363
