import numpy as np

Mat = np.array([[0.9428, 0.3475, -0.8468, 0.4127],
                [0.3475, 1.8423, 0.4759, 1.7321],
                [-0.8468, 0.4759, 1.2147, -0.8621]])

# 对上三角增广矩阵进行回代求解X
def Solution(Mat):
    row = len(Mat)  # Mat矩阵的行数
    column = len(Mat[0])  # Mat矩阵的列数
    X = np.zeros(row) # 结果X向量
    for i in range(row - 1, -1, -1):
        b_i = Mat[i][column - 1]  # bi的值
        for j in range(i + 1, column - 1):
            b_i -= X[j] * Mat[i][j]  # 减去已知解的占比
        X[i] = b_i / Mat[i][i]  # 除以所在行的主元素
    return X, Mat

# 高斯消去法求值
def Gaussian(Mat):
    row = len(Mat)  # Mat矩阵的行数
    column = len(Mat[0])  # Mat矩阵的列数
    for i in range(0,row - 1):  # 矩阵行下标i
        for j in range(i + 1, row):
            # 计算乘数
            m = Mat[j][i] / Mat[i][i]
            # 遍历第j行i到行尾的所有数
            for k in range(i, column):
                # 每个数字进行相减
                Mat[j][k] = Mat[j][k] - m * Mat[i][k]
            # 输出每一步的数学意义
            print("第{}行减去{}乘以第{}行得：".format(j + 1, m, i + 1))
            print(Mat)  # 输出该矩阵
            print()
    return Solution(Mat)

# 列主消元法
def AllElimination(Mat):
    row = len(Mat)  # Mat矩阵的行数
    column = len(Mat[0])  # Mat矩阵的列数
    # 将矩阵化为上三角增广矩阵，并返回此矩阵
    for i in range(row - 1):  # 矩阵行下标
        maximum = 0
        temp = i  # 保存主元行号
        # 寻找列主元行
        for j in range(i, row):
            if Mat[j][i] > maximum:
                maximum = Mat[j][i]
                temp = j
        # 如果当年行不是列主元行，进行交换
        if temp != i:
            for j in range(i, column):
                T = Mat[i][j]
                Mat[i][j] = Mat[temp][j]
                Mat[temp][j] = T
            print("第{}行与第{}行进行交换：".format(i + 1,temp + 1))
            print(Mat)

        for j in range(i + 1, row):
            # 计算乘数
            m = Mat[j][i] / Mat[i][i]
            # 遍历第j行i到行尾的所有数
            for k in range(i, column):
                # 每个数字进行相减
                Mat[j][k] = Mat[j][k] - m * Mat[i][k]
            # 输出每一步的数学意义
            print("第{}行减去{}乘以第{}行得：".format(j + 1, m, i + 1))
            print(Mat)  # 输出该矩阵
            print()

    return Solution(Mat)

if __name__ == '__main__':
    print("1707004716 王浩")
    print("1.使用默认数据 高斯消去法")
    print("2.手动输入数据 高斯消去法")
    print("3.使用默认数据 列主消元法")
    print("4.手动输入数据 列主消元法")
    boolean = int(input("请输入选择："))
    if boolean == 2 or boolean == 4:
        n = int(input("请输入未知数个数："))
        Mat = np.zeros([n, n + 1])
        for i in range(n):
            for j in range(n + 1):
                Mat[i][j] = float(input("请输入第{}行，第{}列数据：".format(i + 1, j + 1)))
        Mat = Mat.astype(np.float)
    print("原矩阵为：")
    print(Mat)
    if boolean == 1 or boolean == 2:
        # 高斯消去法
        X, Mat = Gaussian(Mat)
    elif boolean == 3 or boolean == 4:
        # 列主元消去法
        X, Mat = AllElimination(Mat)
    print("上三角增广矩阵为：")
    print(Mat)
    print("列主元消去法求出的解为：")
    print(X)

