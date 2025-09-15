// 環境設定
export interface EnvConfig {
  MODE: string;
  API_BASE_URL: string;
  DEBUG_MODE: boolean;
}

// 環境別設定読み込み
export const getEnvConfig = (): EnvConfig => {
  // dotenv-webpackで.envファイルから注入される環境変数を参照
  return {
    MODE: process.env.MODE || 'prod',
    API_BASE_URL: process.env.API_BASE_URL || 'https://prod-server/musubi',
    DEBUG_MODE: process.env.DEBUG_MODE === 'true'
  };
};