import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap'
import {createApp, defineComponent, onMounted } from 'vue'
import {useLogin} from "@/modules/login/use-login"
const loginForm = createApp({
    data() {
        const { title } = useLogin()
        return {
            message: title
        }
    }
})
loginForm.mount('#login-form')