# Contest 2
*Legend:*
- ⏳ - in progress
- ✅ - done
- ❌ - undone
## TASKS:
### - Task D [✅]
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

### - Task H [✅]
Peter is working for a company that has built a machine for detecting molecules. Each molecule has a positive integer 
weight. The machine has a detection range $[l;u]$, where $l$ and $u$ are positive integers. The machine can detect a set of 
molecules if and only if this set contains a subset of the molecules with total weight belonging to the machine's 
detection range.

Formally, consider n molecules with weights $w_0,...,w_{n−1}$. The detection is successful if there is a set 
of distinct indices $I = i_1,...,i_m$ such that $l≤w_{i_1}+...+w_{i_m}≤u$.

Due to specifics of the machine, the gap between $l$ and $u$ is guaranteed to be greater than or equal to the weight gap 
between the heaviest and the lightest molecule. Formally $u−l≥w_{max}−w_{min}$, where $w_{max}=max(w_0,...,w_{n−1})$ 
and $w_{min}=min(w_0,...,w_{n−1})$.

Your task is to write a program which either finds any one subset of molecules with total weight within the 
detection range, or determines that there is no such subset.

#### Input
First line contains three integers: the number of molecules $n$ ($1≤n≤200000$) and the endpoints of the detection 
range $l$ and $u$ ($1≤l,u<2^{31}$). Second line contains $n$ integers: $w_0,...,w_{n−1}$ ($1≤w_i<2^{31}$).

#### Output
Print in the first line the size of subset m. Print in the second line the indices of molecules that form any one such 
subset. If there are several correct answers, print any of them.

#### Examples
##### Input #1
4 15 17\
6 8 8 7
##### Answer #1
2\
2 1
##### Input #2
4 14 15\
5 5 6 6
##### Answer #2
0

##### Input #3
4 10 20\
15 17 16 18
##### Answer #3
1\
3 

### - Task I [✅]
Every morning at $8:30$, students at LKS gather for a workout in front of the main building. The boys and girls line up 
on the asphalt, forming a rectangle. On the building's steps stands a photographer. He wants to take a group photo of 
some of the boys and girls. To do this, he needs to select a rectangular area on the asphalt such that the number of 
boys and girls inside the area is equal. Unfortunately for the LKS students, there are many such areas. The photographer
wants to take photos of all possible areas to choose the best one. Each photo takes one second to capture, and during 
this time, the boys and girls will continue their workout!

Imagine that there is a coordinate system drawn on the asphalt in front of the building. The students stand at integer 
points, forming a rectangle of size $h×w$ with sides parallel to the coordinate axes. The photographer needs to select 
a sub-rectangle with sides parallel to the coordinate axes.

Write a program that calculates how many seconds the students will be doing their workout.

#### Input
The first line contains two positive integers $h$ and $w$ ($1≤w$,$h≤300$) — the dimensions of the rectangle formed by 
the students. Each of the following $h$ lines contains $w$ numbers. The number $0$ indicates that a boy is at that 
position, and the number $1$ indicates that a girl is at that position.

#### Output
Print the number of seconds the students will be working out.

#### Examples
##### Input #1
3 4\
0 1 0 0\
1 0 1 1\
0 0 0 1
##### Answer #1
20
##### Input #2
3 1\
0\
0\
0
##### Answer #2
0

### - Task F [✅]
Given a matrix $n×n$, filled with positive integers. The path starts in the upper left corner of the matrix. In one 
move you can go into the next vertical or horizontal cell (if it exists). You can not walk diagonally, you can not 
stay in one place. Find the maximum sum of numbers, standing in the cells on the path of length $k$ (a cell can be 
visited several times).

#### Input
In the first row the integers $n$ and $k$ ($2≤n≤100$,$1≤k≤2000$), separated with a space, are given. Each of the next 
$n$ rows contains $n$ numbers and describe the matrix. All numbers in matrix are integer and lie in the range from $1$ 
to $9999$.

#### Output
Print one number - the maximum sum.

#### Examples
##### Input #1
5 7\
1 1 1 1 1\
1 1 3 1 9\
1 1 6 1 1\
1 1 3 1 1\
1 1 1 1 1
##### Answer #1
21

### - Task E [✅]
Soon there will be team competition «Nowruz Cup 2015». The team must consist of exactly two participants. Amanchik 
wants to participate in it. He found the list of all $2 * n$ ($1 ≤ n ≤ 10^5$) participants including himself. 
Each participant has its own rating. Team rating is the average rating of the two participants. The higher 
the rating the higher team's place. The team takes place number $k + 1$ if there is exactly k teams with strictly 
greater rating.

Of the various partitions find the highest and the lowest place can take Amanchik's team. Amanchik is a participant 
number $1$.
#### Input
First line contains integer $n$. Next line contains $2 * n$ space separated integers $a[i]$ ($1 ≤ a[i] ≤ 10^5$, 
$1 ≤ i ≤ 2 * n$).
#### Output
Print two integers - the highest and the lowest place of Amanchik's team.
#### Examples
##### Input #1
3\
999 3 1 2 1000 1
##### Answer #1
1 2
##### Input #2
1\
1540 1433
##### Answer #2
1 1
##### Input #3
3\
100000 100000 100000 100000 100000 100000
##### Answer #3
1 1
#### Note
In the first example, if we divide the participants in the following way $(999, 2) (3, 1) (1000, 1)$, 
Amanchik's team $(999, 2)$ and team $(1000, 1)$ will take the first place, and the team $(3, 1)$ will take the 
third place. If we divide the participants like $(999, 1) (1000, 2) (3, 1)$, Amanchik's team will take second place. 
Out of the various partitions, the above will meet the highest and lowest places.

### - Task J [✅]
The cows are trying to become better athletes, so Bessie is running on a track for exactly $n$ minutes. 
During each minute, she can choose to either run or rest for the whole minute.

The ultimate distance Bessie runs, though, depends on her exhaustion factor, which starts at $0$. When she chooses 
to run in minute $i$, she will run exactly a distance of $d[i]$ and her exhaustion factor will increase by $1$, but 
must never be allowed to exceed $m$. If she chooses to rest, her exhaustion factor will decrease by $1$ for each 
minute she rests. She cannot commence running again until her exhaustion factor reaches $0$. At that point, she can 
choose to run or rest. At the end of the $n$ minute workout, Bessie's exhaustion factor must be exactly $0$, or she 
will not have enough energy left for the rest of the day.

Find the maximal distance Bessie can run.

#### Input
First line contains two integer $n$ ($1 ≤ n ≤ 10^4$) and $m$ ($1 ≤ m ≤ 500$). Next $n$ lines contains integers 
$d[1], d[2], ..., d[n]$ ($1 ≤ d[i] ≤ 1000$).
#### Output
Print a single integer representing the largest distance Bessie can run while satisfying the conditions.
#### Examples
##### Input #1
5 2\
5\
3\
4\
2\
10
##### Answer #1
9

# Contest 3
## Tasks
### - Task K [✅]
Farmer John's farm was flooded in the most recent storm, a fact only aggravated by the information that 
his cows are deathly afraid of water. His insurance agency will only repay him, however, an amount depending 
on the size of the largest "lake" on his farm. The farm is represented as a rectangular grid with $N (1 ≤ N ≤ 100)$ 
rows and $M (1 ≤ M ≤ 100)$ columns. Each cell in the grid is either dry or submerged, and exactly $K (1 ≤ K ≤ N × M)$ 
of the cells are submerged. As one would expect, a lake has a central cell to which other cells connect by 
sharing a long edge (not a corner). Any cell that shares a long edge with the central cell or shares a long 
edge with any connected cell becomes a connected cell and is part of the lake.
#### Input
 - Line $1$: Three space-separated integers: $N$, $M$, and $K$;
 - Lines $2$, ..., $K+1$: Line $i+1$ describes one submerged location with two space separated integers 
   that are its row and column: $R$ and $C$;
#### Output
 - Line $1$: The number of cells that the largest lake contains.
#### Examples:
##### Input #1
3 4 5\
3 2\
2 2\
3 1\
2 3\
1 1
##### Answer #1
4

