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
      }
    }
  },

  publicPath: undefined,
  runtimeCompiler: undefined,
  productionSourceMap: undefined,
  parallel: undefined,
  css: undefined
}