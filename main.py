from sklearn.tree import DecisionTreeRegressor
from dtreeviz.trees import dtreeviz
from sklearn import tree
from sklearn.model_selection import train_test_split
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import figure

data = pd.read_csv("BoxingData.csv")
data.info()

x = np.array(data.drop("Win Rate", axis=1))
y = np.array(data["Win Rate"])

x_train, x_test, y_train, y_test = train_test_split(x, y)

model = DecisionTreeRegressor(random_state=42)
model.fit(x_train, y_train)
print(model.score(x_test, y_test))

figure = plt.figure(figsize=(100,100), dpi=90)
tree.plot_tree(model, filled=True, fontsize=5)
plt.show()

plt.xlabel("Height (cm)")
plt.ylabel("Win Rate")
plt.scatter(x_test[:,6], y_test, color="red")
plt.show()

plt.xlabel("Win Rate")
plt.ylabel("Boxers")
plt.hist(y_test, bins=10)
plt.show()