import {ref} from "vue";

export const useLogin = () => {

    const message = ref<string | undefined>()
    message.value = "ログイン情報を入力してください(Vue側定義)"
    return { message }
}