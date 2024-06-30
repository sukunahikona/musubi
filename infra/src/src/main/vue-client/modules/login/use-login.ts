import {ref} from "vue";

export const useLogin = () => {

    const title = ref<string | undefined>()
    title.value = "ログイン画面(Vue側定義)"
    return { title }
}