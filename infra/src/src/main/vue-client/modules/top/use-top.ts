import apiClient from "@/modules/common/my-axios"
export const useTop = () => {

    const onBtnClick = () => {
        console.log('onBtnClick start')
        apiClient.get('/api/search?zipcode=7830060')
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
             })
        console.log('onBtnClick end')
    }
    return { onBtnClick }
}