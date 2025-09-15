import "bootstrap/dist/css/bootstrap";
import 'bootstrap'
import { createApp, defineComponent, onMounted } from 'vue'
import { useLogin } from "@/modules/login/use-login"
import { getEnvConfig } from "@/config/env"

const loginForm = createApp({
    data() {
        const { message } = useLogin()
        const envConfig = getEnvConfig()
        
        // 環境別の処理例
        if (envConfig.DEBUG_MODE) {
            console.log('Debug mode enabled')
            console.log('API Base URL:', envConfig.API_BASE_URL)
        }

        const contentMessage = '[' + envConfig.MODE + ']' + message.value

        return {
            message: contentMessage,
            environment: envConfig.MODE,
            apiUrl: envConfig.API_BASE_URL
        }
    }
})
loginForm.mount('#login-form')