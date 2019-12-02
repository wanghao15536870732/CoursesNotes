import numpy as np
import matplotlib.pyplot as plt
# 原函数
def function(x):
    return x ** 3 - 2 * x - 5

# 导数
def function1(x):
    return 3 * x ** 2 - 2

# 点斜式求函数
def func(x0,f,x):
    return f * (x - x0) + function(x0)

def newtonian(num):
    x0 = num
    x1 = x0 - function(x0) / function1(x0)
    first = [x0]
    second = [x1]
    plt.scatter(x0,function(x0))
    line1 = np.arange(x1, x0, 0.001)
    plt.plot(line1, func(x0, function1(x0), line1))
    plt.scatter(x1, 0)
    plt.scatter(x1,function(x1))
    plt.plot([x1,x1],[0,function(x1)],color='red',linestyle='--')

    while np.abs(x1 - x0) > 1e-4:
        x0 = x1
        x1 = x0 - function(x0) / function1(x0)
        first.append(x0)
        second.append(x1)

    print("该方程在{}附近的根为：{}".format(num,x1))
    return x1

if __name__ == '__main__':
    print("1707004716 王浩")
    left = float(input("请输入左边界："))
    right = float(input("请输入右边界："))
    number = float(input("请输入根附近的值："))
    line = np.arange(left, right, 0.001)  # x坐标范围
    plt.title("newtonian")  # 图标标题
    plt.xlabel("x")  # x轴标题
    plt.ylabel("y")  # y轴标题
    plt.plot(line, function(line))  # 画出曲线
    plt.grid(True)  # 显示网格
    newtonian(number)
    plt.show()