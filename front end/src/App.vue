<template>
  <div class = "header align-center">
    <ul>
      <li @click="test" class = "menu-item logo">
        <a href="#" class = "no-decor">
          samsung
        </a>
      </li>
      <li class="smart menu-item">
        <a href="javascript:void(0)" class = "no-decor">
          Thiết bị thông minh
        </a>
        <br>
        <div class="smart-content">
          <a href="#" class = "no-decor">
            Smartphone
          </a>
          <a href="#" class = "no-decor">
            Tablet
          </a>
          <a href="#" class = "no-decor">
            Smartwatch
          </a>
          <a href="#" class = "no-decor">
            Phụ kiện
          </a>
        </div>
      </li>
      <li class="tv-av menu-item">
        <a href="javascript:void(0)" class = "no-decor">
          TV & AV
        </a>
        <br>
        <div class="tv-content">
          <a href="#" class = "no-decor">
            TV
          </a>
          <a href="#" class = "no-decor">
            Thiết bị nghe nhìn
          </a>
          <a href="#" class = "no-decor">
            Máy chiếu
          </a>
        </div>
      </li>
      <li class="home-appliance menu-item">
        <a href="javascript:void(0)" class = "no-decor">
          Gia dụng
        </a>
        <br>
        <div class="appliance-content">
          <a href="#" class = "no-decor">
            Tủ lạnh
          </a>
          <a href="#" class = "no-decor">
            Máy giặt
          </a>
          <a href="#" class = "no-decor">
            Làm sạch không khí
          </a>
          <a href="#" class = "no-decor">
            Dụng cụ nhà bếp
          </a>
        </div>
      </li>
      <li class = "menu-item right-float">
        <a href="#" class = "no-decor right-float">
          Login/Sign in
        </a>
      </li>
      <router-view/>
      <li class = "menu-item input-box right-float">
        <input type = "text" class = "input-field" placeholder = "input search">
        <img class = "search-icon right-float" src = "@/assets/search-icon.png">
      </li>
    </ul>
  </div>
  <div>
    <div id="carousel-1" class="carousel slide custom-carousel" data-bs-ride="carousel" style="height: 600px;">
      <div class="carousel-inner h-100">
        <div class="carousel-item active h-100"><img class="w-100 d-block position-absolute h-100 fit-cover" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image" style="z-index: -1;" />
          <div class="container d-flex flex-column justify-content-center h-100">
            <div class="row">
              <div class="col-md-6 col-xl-4 offset-md-2">
                <div style="max-width: 350px;">
                  <h1 class="text-uppercase fw-bold">Biben dum<br />fringi dictum, augue purus</h1>
                  <p class="my-3">Tincidunt laoreet leo, adipiscing taciti tempor. Primis senectus sapien, risus donec ad fusce augue interdum.</p><a class="btn btn-primary btn-lg me-2" role="button" href="#">Button</a><a class="btn btn-outline-primary btn-lg" role="button" href="#">Button</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item h-100"><img class="w-100 d-block position-absolute h-100 fit-cover" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image" style="z-index: -1;" />
          <div class="container d-flex flex-column justify-content-center h-100">
            <div class="row">
              <div class="col-md-6 col-xl-4 offset-md-2">
                <div style="max-width: 350px;">
                  <h1 class="text-uppercase fw-bold">Biben dum<br />fringi dictum, augue purus</h1>
                  <p class="my-3">Tincidunt laoreet leo, adipiscing taciti tempor. Primis senectus sapien, risus donec ad fusce augue interdum.</p><a class="btn btn-primary btn-lg me-2" role="button" href="#">Button</a><a class="btn btn-outline-primary btn-lg" role="button" href="#">Button</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item h-100"><img class="w-100 d-block position-absolute h-100 fit-cover" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image" style="z-index: -1;" />
          <div class="container d-flex flex-column justify-content-center h-100">
            <div class="row">
              <div class="col-md-6 col-xl-4 offset-md-2">
                <div style="max-width: 350px;">
                  <h1 class="text-uppercase fw-bold">Biben dum<br />fringi dictum, augue purus</h1>
                  <p class="my-3">Tincidunt laoreet leo, adipiscing taciti tempor. Primis senectus sapien, risus donec ad fusce augue interdum.</p><a class="btn btn-primary btn-lg me-2" role="button" href="#">Button</a><a class="btn btn-outline-primary btn-lg" role="button" href="#">Button</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item"><img class="w-100 d-block" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image" /></div>
      </div>
      <div><a class="carousel-control-prev" href="#carousel-1" role="button" data-bs-slide="prev"><span class="carousel-control-prev-icon"></span><span class="visually-hidden">Previous</span></a><a class="carousel-control-next" href="#carousel-1" role="button" data-bs-slide="next"><span class="carousel-control-next-icon"></span><span class="visually-hidden">Next</span></a></div>
      <ol class="carousel-indicators">
        <li class="active" data-bs-target="#carousel-1" data-bs-slide-to="0"></li>
        <li data-bs-target="#carousel-1" data-bs-slide-to="1"></li>
        <li data-bs-target="#carousel-1" data-bs-slide-to="2"></li>
        <li data-bs-target="#carousel-1" data-bs-slide-to="3"></li>
      </ol>
    </div>
  </div>




  <div class = "main-body">
    <products>

    </products>

    <br>
    <br>

    <discount>

    </discount>
  </div>


  <div class = "footer">
			
  </div>
		
	
						
</template>

<script>
  // import DiscountProduct from '@/components/Discount.vue'
  import ProductList from '@/components/Product.vue'
  import DiscountProduct from '@/components/Discount.vue'
  var products = ProductList
  var discount = DiscountProduct

  export default{
    components:{
      products, discount
    },
    methods:{
      async test(){
        let a = 1;
        console.log(a);
        this.testPromis().then(res => {
          console.log(res);

        }).catch(er => {
          console.log(er);

        });
      },
      testPromis(){
        return new Promise((resolveOuter) => {
          resolveOuter(
            new Promise((resolveInner) => {
              resolveInner([1,2,3])
            })
          );
        });
      }
    }
  }
</script>