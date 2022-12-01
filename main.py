from sklearn.tree import DecisionTreeRegressor
from sklearn.model_selection import train_test_split
import pandas as pd
import numpy as np

data = pd.read_csv("BoxingData.csv")
data.info()

data.drop("Debut", axis=1, inplace=True)

x = np.array(data.drop("Win Rate", axis=1))
y = np.array(data["Win Rate"])

x_train, x_test, y_train, y_test = train_test_split(x, y)

model = DecisionTreeRegressor(max_depth=5)
model.fit(x_train, y_train)
print(model.score(x_test, y_test))