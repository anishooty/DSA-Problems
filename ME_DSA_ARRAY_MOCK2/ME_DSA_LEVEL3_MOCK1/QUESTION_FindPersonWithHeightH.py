def findRotationPoint(seq):
    left, right = 0, len(seq) - 1
    while left < right:
        mid = left + (right - left) // 2
        if seq[mid] > seq[right]:
            left = mid + 1
        else:
            right = mid
    return left


def binarySearch(seq, left, right, target):
    while left <= right:
        mid = left + (right - left) // 2
        if seq[mid] == target:
            return mid
        elif seq[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1


def findPersonWithHeightH(seq, H):
    rotationPoint = findRotationPoint(seq)
    n = len(seq)

    left_result = binarySearch(seq, 0, rotationPoint - 1, H)
    if left_result != -1:
        return left_result

    right_result = binarySearch(seq, rotationPoint, n - 1, H)
    if right_result != -1:
        return right_result

    return -1


if __name__ == '__main__':
    n = int(input().strip())
    seq = [int(i) for i in input().strip().split()]
    q = int(input().strip())

    for _ in range(q):
        H = int(input().strip())
        print(findPersonWithHeightH(seq, H))
