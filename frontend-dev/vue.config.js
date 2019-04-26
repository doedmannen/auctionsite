module.exports = {
  outputDir: './../src/main/resources/static/',
  assetsDir: '/assets/',

  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        ws: true,
        changeOrigin: true
      },
      '/assets': {
        target: 'http://localhost:8080/',
        ws: true,
        changeOrigin: true
      },
      '/login': {
        target: 'http://localhost:8080/',
        ws: true,
        changeOrigin: true
      },
      '/logout': {
        target: 'http://localhost:8080/',
        ws: true,
        changeOrigin: true
      },
      // '/websocket': {
      //   target: 'http://localhost:8080/',
      //   ws: true,
      //   changeOrigin: true
      // }
    }
  },

  publicPath: undefined,
  runtimeCompiler: undefined,
  productionSourceMap: undefined,
  parallel: undefined,
  css: undefined
}
