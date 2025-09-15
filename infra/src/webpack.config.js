const path = require('path');
const glob = require('glob');
const Dotenv = require('dotenv-webpack');

const srcDir = "./src/main/vue-client/pages"
const entries = glob
    .sync('**/*.ts', {//pages配下のすべてのtsファイルをターゲットにビルドをかける
        cwd: srcDir
    })
    .map(function (key) {
        // 後続でoutputのファイル名を指定する際にmapのkey値を利用するのでreplaceしておく
        return [key.replace('.ts',''), path.resolve(srcDir, key)];
    });

const entryObj = Object.fromEntries(entries);

module.exports = (env, argv) => {
  // 環境ファイルパスを直接指定
  const envFile = env && env.ENV_FILE ? env.ENV_FILE : '.env/.env.prod';
  
  return {
    entry: entryObj,
    output: {
      path: path.join(__dirname, 'src/main/resources/static/js'),
      filename: "[name].js",//name値はentriesで作成したmap要素のkey値を参照
      clean: true
    },
    resolve: {
      extensions: ['.webpack.js', '.web.js', '.ts', '.tsx', '.js', '.css', '.min.css', '.scss'],
      alias: {
        vue: "vue/dist/vue.esm-bundler.js",
        '@': path.resolve(__dirname, './src/main/vue-client')
      }
    },
    module: {
      rules: [
        { test: /\.ts?$/, loader: 'ts-loader' },
        {
          // CSS, Sass, SCSSファイルを対象とする
          test: /\.(css|s[ac]ss)$/i,
          use: [
            // linkタグに出力する機能
            "style-loader",
            // CSSをバンドルするための機能
            "css-loader",
            // sass2css（CSSファイルの場合はスキップされる）
            {
              loader: "sass-loader",
              options: {
                api: "modern-compiler" // モダンAPIを使用
              }
            },
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
    plugins: [
      new Dotenv({
        path: envFile,
        safe: false,
        systemvars: false
      })
    ]
  };
}
