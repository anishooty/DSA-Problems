from typing import List

def validSudoku(grid: List[List[str]]) -> bool:
    for row in grid:
        if not is_valid(row):
            return False
    
    for col in range(9):
        column = [grid[row][col] for row in range(9)]
        if not is_valid(column):
            return False
    
    for i in range(0, 9, 3):
        for j in range(0, 9, 3):
            subgrid = [grid[x][y] for x in range(i, i + 3) for y in range(j, j + 3)]
            if not is_valid(subgrid):
                return False
    
    return True

def is_valid(nums: List[str]) -> bool:
    seen = set()
    for num in nums:
        if num != '.':
            if num in seen:
                return False
            seen.add(num)
    return True

if __name__ == '__main__':
    grid = []
    for i in range(9):
        nums = input().split()
        grid.append(nums)
    if validSudoku(grid):
        print('true')
    else:
        print('false')


'''
Crio Methodology

Milestone 1: Understand the problem clearly
1. Ask questions & clarify the problem statement clearly.
2. Take an example or two to confirm your understanding of the input/output

Milestone 2: Finalize approach & execution plan
1. Understand what type of problem you are solving and see if you can recollect solving similar problems in the past
      a. Obvious logic (this would only test ability to convert logic to code)
      b. Figuring out logic
      c. Knowledge of specific algorithm, data structure or pattern
      d. Knowledge of specific domain or concepts
      e. Mapping real world into abstract concepts/data structures
2. Brainstorm multiple ways to solve the problem and pick one based on the TC/SC requirements
3. Get to a point where you can explain your approach to a 10 year old

Milestone 3 : Come up with an Instruction Manual for a 10 year old
1. Take a stab at the high-level logic & write it down like a detailed Instruction Manual for a 10 Year old where each line of the manual can be expanded into a logical line(s) of code.
2. Try to offload logic out of the main section as much as possible by delegating to functions.

Milestone 4: Code by expanding your 10 Year Olds "Instruction Manual
1. Run your code snippets at every logical step to test correctness (Helps avoid debugging larger pieces of code later)
2. Make sure you name the variables, functions clearly.
3. Use libraries as much as possible

Milestone 5: Prove that your code works using custom test cases
1. Make sure you check boundary conditions and other test cases you noted in Milestone 1
      a. If compiler is not available, dry run your code on a whiteboard or paper
2. Suggest optimizations if applicable during interviews
'''