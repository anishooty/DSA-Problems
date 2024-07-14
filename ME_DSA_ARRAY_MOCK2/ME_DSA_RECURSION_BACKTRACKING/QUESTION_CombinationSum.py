from typing import List

def combinationSum(n: int, target: int, candidates: List[int]) -> List[List[int]]:
    result = []
    candidates.sort()
    
    def backtrack(start, target, combination):
        if target == 0:
            result.append(combination[:])
            return
        
        for i in range(start, len(candidates)):
            if candidates[i] > target:
                break
            
            combination.append(candidates[i])
            backtrack(i, target - candidates[i], combination)
            combination.pop()
    
    backtrack(0, target, [])
    
    return result

def main():
    n, target = map(int, input().strip().split())
    candidates = [int(input()) for _ in range(n)]
    
    combinations = combinationSum(n, target, candidates)
    
    if combinations:
        for combination in combinations:
            print(' '.join(map(str, combination)))
    else:
        print("NA")

if __name__ == "__main__":
    main()
