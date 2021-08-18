
// Vue3 관련 설정 파일
module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../src/main/resources/static/index.html",
  devServer: {
    port: '8083',
    proxy: 'http://localhost:8080',
  },
  chainWebpack: config =>{
     const svgRule = config.module.rule("svg");    
    svgRule.uses.clear();    
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  }
}
