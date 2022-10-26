<template>
    <div class = "title-section">
        Sản phẩm mới 
    </div>
    <div class = "row product-section flex-box">
        <div class = "col-md-3 right-border flex-1" v-for = "product in products.slice(0,4)" :key = "product.pid">
            <img class = "product-img" src = "@/assets/zflip.jpg">
            <div class = "product-spec">
            <h4>
                {{product.pname}}
            </h4>
            <h5>
                price: {{product.price}}
            </h5>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            </div>
        </div>
    </div>
</template>
<script>
    import ProductService from "@/ProductService"

    export default {
        name: 'ProductList',
        data(){
            return {
            products: [],
            };
        },
        created(){
            this.retrieveProducts();
        },
        methods:{
            retrieveProducts(){
                ProductService.getAll().then(response => {
                    this.products = response.data;
                    console.log(response.data);
                }).catch(e => {
                    console.log(e);
                    console.log("1234");
                });
            },
            
        }
    }
</script>