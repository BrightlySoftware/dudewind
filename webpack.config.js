const ExtractTextPlugin = require('extract-text-webpack-plugin')
const path = require('path')

module.exports = {
  entry: './main.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'styles.css',
  },
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: [
            { loader: 'css-loader', options: { importLoaders: 1 } },
            'sass-loader',
            'postcss-loader'
          ]
        })
      }
    ]
  },
  plugins: [
    new ExtractTextPlugin('styles.css')
  ]
}
