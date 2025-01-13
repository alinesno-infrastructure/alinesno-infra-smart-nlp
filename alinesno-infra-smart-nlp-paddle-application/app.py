# -*- coding: utf-8 -*-

import time

from datetime import timedelta
from flask import Flask, request, jsonify
from paddlenlp import Taskflow

app = Flask(__name__)

app.config['JSON_AS_ASCII'] = False  # 设置 JSON 编码为 UTF-8，用于处理中文
app.config['SEND_FILE_MAX_AGE_DEFAULT'] = timedelta(hours=1)  # 设置发送文件的默认缓存时间为 1 小时
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024  # 设置上传文件的最大尺寸为 16MB

@app.route('/hello', methods=['POST'])
def detect():
    text = request.form['text']
    print('text = ' + text)

    result = senta(text)

    return jsonify({
        'status': 'success',
        'data': result,  # 返回识别结果列表
        'text': text
    })

#  @app.route('/hello', methods=['POST'])
#  def detect():
    #  text = request.files['text']
    #  print('text = ' + text)

    #  input_features = tokenizer("你好！请自我介绍一下。", return_tensors="pd")
    #  outputs = model.generate(**input_features, max_length=128)
    #  result = outputs

    #  return jsonify({
        #  'status': 'success',
        #  'data': result,  # 返回识别结果列表
        #  'usage_time': '{:.4f}s'.format(start_time - end_time)  # 返回识别所用时间
    #  })

if __name__ == '__main__':

    #  tokenizer = AutoTokenizer.from_pretrained("linly-ai/chinese-llama-2-7b")
    #  model = AutoModelForCausalLM.from_pretrained("linly-ai/chinese-llama-2-7b", dtype="float32")

    schema = ['情感倾向[正向，负向]']
    senta = Taskflow("sentiment_analysis", model="uie-senta-base", schema=schema)

    app.run(host='0.0.0.0', port=5003, debug=True, threaded=True, processes=1)  # 运行 Flask 应用
