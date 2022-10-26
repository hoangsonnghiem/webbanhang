
import axios from "axios";
const PRODUCT_BASE_URL = 'http://localhost:8081/product'

class ProductService{
    getAll()
    {
        return axios.get(PRODUCT_BASE_URL + "/");
    }

    getProductsOrderByDiscount()
    {
        return axios.get(PRODUCT_BASE_URL + "/discount");
    }

    saveProduct(data){
        return axios.post(PRODUCT_BASE_URL, data);
    }
}

export default new ProductService()