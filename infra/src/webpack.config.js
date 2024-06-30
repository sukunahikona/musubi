const path = require('path');
const glob = require('glob');

const srcDir = "./src/main/vue-client/pages"
const entries = glob
    .sync('**/index.ts', {//一旦、index.tsのみをターゲットにビルドをかける
        cwd: srcDir
    })
    .map(function (key) {
        // 後続でoutputのファイル名を指定する際にmapのkey値を利用するのでreplaceしておく
        return [key.replace('.ts',''), path.resolve(srcDir, key)];
    });

const entryObj = Object.fromEntries(entries);

module.exports = {
  entry: entryObj,
  output: {
    path: path.join(__dirname, 'src/main/resources/static/js'),
    filename: "[name].js",//name値はentriesで作成したmap要素のkey値を参照
    clean: true
  },
  resolve: {
    extensions: ['.webpack.js', '.web.js', '.ts', '.tsx', '.js']
  },
  resolve: {
    alias: {
      vue: "vue/dist/vue.esm-bundler.js",
      '@': path.resolve(__dirname, './src/main/vue-client')
    },
    extensions: ['.ts','.js']
  },
  module: {
    rules: [
      { test: /\.ts?$/, loader: 'ts-loader' },
      { test: /\.css$/, use: ['style-loader', 'css-loader']},
      {
        // 拡張子がsassとscssのファイルを対象とする
        test: /\.s[ac]ss$/i,
        // ローダー名
        use: [
          // linkタグに出力する機能
          "style-loader",
          // CSSをバンドルするための機能
          "css-loader",
          // sass2css
          "sass-loader",
        ],
      },
      {
        // 対象となるファイルの拡張子
        test: /\.(gif|png|jpg|eot|wof|woff|ttf|svg)$/,
        // 画像をBase64として取り込む
        type: "asset/inline",
      }
    ]
  },
}
