<template>
    <div class = "title-section">
       Sản phẩm giá sốc
    </div>
    <div class = "row product-section flex-box">
        <div class = "col-md-3 right-border flex-1" v-for = "product in discounts.slice(0,4)" :key = "product.pid">
            <img class = "product-img" src = "@/assets/zfold.jpg">
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
        name: 'DiscountProduct',
        data(){
            return {
            discounts: [],
            };
        },
        created(){
            this.retrieveProducts();
        },
        methods:{
            retrieveProducts(){
            ProductService.getProductsOrderByDiscount().then(response => {
                this.discounts = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
            },
        }
    }
</script>