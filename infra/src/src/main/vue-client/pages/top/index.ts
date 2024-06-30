import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import '@/style/top/top.css'
import '@/style/top/top.scss'
import {createApp, defineComponent, onMounted } from 'vue'
import {useTop} from '@/modules/top/use-top'
const topApp = createApp({
    data() {

        const { onBtnClick } = useTop()

        return {
            message: 'TOP(Vue側本体定義)',
            onBtnClick: onBtnClick
        }
    }
})
topApp.mount('#top')