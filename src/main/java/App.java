import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args){
    //staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Brand> brands = Brand.all();
      List<Store> stores = Store.all();
      model.put("stores", stores);
      model.put("brands", brands);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String brandName = request.queryParams("brandName");
      Brand newBrand = new Brand(brandName);
      newBrand.save();
      response.redirect("/");
      return null;
    });

    post("/Brands_delete", (request, response) -> {
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      Brand deadBrand = Brand.find(brandId);
      deadBrand.delete();
      response.redirect("/");
      return null;
    });

    post("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String storeName = request.queryParams("storeName");
      Store newStore = new Store(storeName);
      newStore.save();
      response.redirect("/");
      return null;
    });

    post("/stores_delete", (request, response) -> {
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      Store deadStore = Store.find(storeId);
      deadStore.delete();
      response.redirect("/");
      return null;
    });

    get("/brands/:brand_id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.params("brand_id"));
      Brand brand = Brand.find(brand_id);
      List<Store> storesInBrand = brand.getStores();
      model.put("storesInBrand", storesInBrand);
      model.put("brand", brand);
      model.put("allStores", Store.all());
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/:brand_id/add", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      Brand brand = Brand.find(brandId);
      int newStoreId = Integer.parseInt(request.queryParams("storeid"));
      Store newStore = Store.find(newStoreId);
      newStore.addBrand(brand);
      String brandIdPath = String.format("/brands/%d", brandId);
      response.redirect("/brands/"+ brandId);
      return null;
    });


        post("/stores/:store_id/add", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          int storeId = Integer.parseInt(request.queryParams("store_id"));
          Store newStore=Store.find(storeId);
          int brandId = Integer.parseInt(request.queryParams("brandid"));
          Brand newBrand = Brand.find(brandId);
          newStore.addBrand(newBrand);
          String brandIdPath = String.format("/brands/%d", brandId);
          response.redirect("/stores/"+ storeId);
          return null;
        });


    get("/stores/:store_id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int storeId = Integer.parseInt(request.params("store_id"));
      Store store = Store.find(storeId);
      List<Brand> brandsList = store.getBrands();
      model.put("brandsList", brandsList);
      model.put("store", store);
      model.put("allBrands", Brand.all());
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

   post("/stores/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      String newName = request.queryParams("newName");
      Store store = Store.find(storeId);
      store.updateName(newName);

      model.put("store", store);
      model.put("allBrands", Brand.all());
      //model.put("template", "templates/store.vtl");
      response.redirect("/stores/"+ storeId);
      return null;
    });


    // post("/stores/:store_id/add", (request, response) -> {
    //   int studId = Integer.parseInt(request.queryParams("store_id"));
    //   Store store = Store.find(studId);
    //   int newBrandId = Integer.parseInt(request.queryParams("brandid"));
    //   Brand newBrand = Brand.find(newBrandId);
    //   newBrand.addStore(store);
    //   String studIdPath = String.format("/stores/%d", studId);
    //   response.redirect(studIdPath);
    //   return null;
    // });
  }
}
