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

### - Task H [⏳]
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