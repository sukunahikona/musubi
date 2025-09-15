import axios, { AxiosInstance } from "axios"

const apiClient: AxiosInstance = axios.create({
    baseURL: "https://zipcloud.ibsnet.co.jp",
    headers: {
        "Content-type": "application/json",
    }
});
export default apiClient;