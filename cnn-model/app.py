from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from PIL import Image

app = Flask(__name__)

# Load your trained model (replace 'model.h5' with your file)
model = tf.keras.models.load_model('model.h5')
classes = ['Cotton', 'Silk', 'Denim', 'Wool']

@app.route('/predict', methods=['POST'])
def predict():
    file = request.files['image']
    img = Image.open(file).resize((224, 224))
    img_array = np.array(img) / 255.0
    img_array = np.expand_dims(img_array, axis=0)

    prediction = model.predict(img_array)
    result = classes[np.argmax(prediction)]

    return jsonify({'fabric': result})

if __name__ == '__main__':
    app.run(debug=True)
