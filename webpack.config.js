'use strict';

var webpack = require('webpack');
var autoprefixer = require('autoprefixer');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');

var ENV = process.env.npm_lifecycle_event;
var isProd = ENV === 'build';

module.exports = function makeWebpackConfig () {

  var config = {};
  var developmentPath = __dirname + '/front-end/';
  var buildPath = __dirname + '/src/main/resources/static/';

  config.entry = {
    app: developmentPath + 'js/app.js'
  };

  config.output = {
    path: buildPath,
    publicPath: isProd ? './' : '/',
    filename: 'vendor/[name].[hash].js',
    chunkFilename: 'vendor/[name].[hash].js'
  };

  if (isProd) {
    config.devtool = 'source-map';
  } else {
    config.devtool = 'eval-source-map';
  }

  config.module = {
    preLoaders: [],
    loaders: [
      {
        test: /\.js$/,
        loader: 'babel',
        exclude: /node_modules/,
        query: {
          presets: ['es2015']
        }
      },
      {
        test: /\.scss$/,
        loader: ExtractTextPlugin.extract('style', 'css!postcss!sass', {publicPath: '/'})
      },
      {
        test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot|otf)$/,
        loader: 'url-loader?limit=10000&name=vendor/[name].[hash].[ext]'
      },
      {
        test: /\.html$/,
        loader: 'raw'
      }
    ]
  };

  config.postcss = [
    autoprefixer({
      browsers: ['last 2 version', 'iOS 7']
    })
  ];

  config.plugins = [];
  config.plugins.push(
      new HtmlWebpackPlugin({
        template: developmentPath + 'index.html',
        inject: 'body'
      }),
      new ExtractTextPlugin('vendor/[name].[hash].css', {disable: !isProd, allChunks: true})
  );

  if (isProd) {
    config.plugins.push(
        new webpack.NoErrorsPlugin(),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin(),
        new CopyWebpackPlugin([{
          from: developmentPath + 'public'
        }])
    )
  }

  config.devServer = {
    contentBase: developmentPath + 'public/',
    stats: 'minimal',
    port: 4200,
    proxy: {
      '*': 'http://localhost:8080/'
    },
    host: '0.0.0.0'
  };

  return config;
}();