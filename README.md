# An example of using Data Binding Library + MVVM in Android 

[![Made in SteelKiwi](https://github.com/steelkiwi/Getting-started-with-Kotlin/blob/master/made_in_steelkiwi.png)](http://steelkiwi.com/blog/)

This project was designed to demonstrate implementation of MVVM and Data Binding Library


## What is data binding? 
Data Binding Library was firstly represented on Google I/O 2015.  This library enable to bind code logic which represents behaviour of UI with layout files just adding small parts of code to XML. 


## What is the profit of it? 

### Advantages
 - Increase speed of development 
 - Сode is clear and structured
 - Reduce amount of boilerplate code 
 - Automatically updating view state when bound data was changed
 - Ability to add custom binding adapters and conversions which allows to bind view with data in your own custom way 

### Disadvantages 
 - It requires some time for learning and good base skills 
 - Sometimes it is hard to debug if you don't know where you make mistake in xml file  
 - You will never want to go back to your previous code-style :) 



# Project's Details 
 In this article will be described basic steps for creating simple REST-client app "AndroidMovieFinder" which will allow users to find movies and short overview of it by typing search word. Sample project uses TheMovieDB API for retrieving data. More about their API you can find here: [Overview of The Movie DB](https://www.themoviedb.org/about).


<Screenshots of the app>

### !!!!!! to do
### **Here will be two screenshots (or gifs) of the sample project **
### !!!!! to do

## Data Binding Library Implementation

* Check project's Android Plugin for Gradle (build.gradle file of the project). The version of plugin should be **'1.5.0-alpha1'** or higher.
	If you have later version please update it as described here: [How to update gradle plugin](https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin)

* When project was created it is necessary to enable Data Binding by adding few lines of code to **'build.gradle'** file in the app module:   

```java
android {
 // here you also can find project settings such as: compileSdkVersion, 
 // buildToolsVersion, defaultConfig and buildTypes

 // enabling data binding
 dataBinding {
  enabled = true
 }
}
```
* After completing steps described above try to synchronize project. 




## Search screen 

Let's observe what is going on search screen. There are field for typing search word, button for starting searching and list for displaying results on this screen.


### Xml file for View


**activity_main.xml**

```xml
<layout xmlns:app="http://schemas.android.com/apk/res-auto">
   <data>
       <variable
           name="viewModel"
           type="mvvm.steelkiwi.com.moviefinder.ui.main.MainActivityViewModel" />
   </data>

   <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools"
       android:id="@+id/activity_main"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context="mvvm.steelkiwi.com.moviefinder.ui.main.MainActivity">

       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:orientation="vertical">

           <LinearLayout
               android:layout_weight="0"
               android:layout_width="match_parent"
               android:layout_height="@dimen/toolbar_height"
               android:background="@color/colorPrimary"
               android:elevation="@dimen/toolbar_elevation"
               android:orientation="horizontal">

               <TextView
                   android:layout_width="match_parent"
                   android:layout_height="match_parent"
                   android:layout_weight="1"
                   android:gravity="center_vertical"
                   android:padding="16dp"
                   android:layout_marginLeft="16dp"
                   android:text="@string/app_name"
                   android:textColor="@android:color/white"
                   android:textSize="@dimen/material_text_title" />
           </LinearLayout>


           <LinearLayout
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:layout_weight="0"
               android:orientation="horizontal">

               <EditText
                   android:id="@+id/editText"
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:layout_weight="1"
                   android:hint="@string/hint_edit_text_movie_name"
                   android:maxLines="1"
                   android:padding="16dp"
                   android:singleLine="true"
                   android:text="@={viewModel.enteredQuery}" />

               <Button
                   android:id="@+id/button"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   android:layout_marginRight="8dp"
                   android:layout_weight="0"
                   android:background="@drawable/selector_blue_button"
                   android:enabled="@{!viewModel.isLoading}"
                   android:onClick="@{viewModel::onFindMovieClick}"
                   android:src="@drawable/ic_find"
                   android:text="Find"
                   android:textColor="@android:color/white" />
           </LinearLayout>

           <android.support.v7.widget.RecyclerView
               android:layout_width="match_parent"
               android:layout_height="match_parent"
               android:layout_weight="1"
               android:visibility="@{!viewModel.isLoading}"
               app:configuration="@{viewModel.recyclerConfiguration}" />

           <TextView
               android:layout_weight="0"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:gravity="center"
               android:textColor="@android:color/white"
               android:textSize="@dimen/material_text_subhead"
               android:text="@string/loading_next_page"
               android:padding="8dp"
               android:visibility="@{viewModel.isLoadingNextPage}"
               android:background="@color/blue_gray"
               android:layout_gravity="center" />
       </LinearLayout>

       <ProgressBar
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_gravity="center"
           android:visibility="@{viewModel.isLoading}" />

       <!-- use  &amp;&amp; for logical && operator -->
       <TextView
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_gravity="center"
           android:text="@string/no_data"
           android:textSize="@dimen/material_text_title"
           android:visibility="@{viewModel.moviesItems.size() == 0 &amp;&amp; !viewModel.isLoading}" />
   </FrameLayout>
</layout>
```
 - You can notice that the root attribute is **`<layout>`**. It is used to define that this file is using by Data Binding Library. 

 - Within **`<data>`** attribute there is a variable which name is **'viewModel'** and type is **`MainActivityViewModel.java`**. This variable is an instance of class which hold bound data for views. 

 - **`@{}`** syntax is used to mark that you want to use data binding variable which you have added to **`<data>`** attribute. 
	In brackets **`{  }`** you can get values from declared variable by using **`.`**. For example: **`<nameOfVariable>.<Value>`**. If you want  to call some method of variable you should use **`::`** . For example: **`<nameOfVariable>::<MethodName>`**. 


### View Model class 

**`MainActivityViewModel.java`** is a view model class for storing data which is bound with views. In this class most lines of codes are well commented so it is clearly for understanding.

**Note:** this class extends base  **`ActivityViewModel<T>`** class which is described below. 

```java
public class MainActivityViewModel extends ActivityViewModel<MainActivity> implements MainActivityContract {

   private MainActivity activity;
   private MoviesModel moviesModel; // model for getting results by entered search word
   public final ObservableBoolean isLoading = new ObservableBoolean(); // boolean variable for changing visibility of progress bar view
   private final ObservableBoolean isLoadingNextPage = new ObservableBoolean(); // for showing or hiding container which marked that next page is loading
   public final ObservableField<String> enteredQuery = new ObservableField<>(); // query from editView
   private String searchedQuery; // query which is already searched (can be null at startl)

   public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration(); // for configuring recycler view

   private final int DEVICE_ITEM_LAYOUT = R.layout.item_movie_preview; // id of list item

   private ArrayList<MovieDTO> moviesItems = new ArrayList<>(); // collection for storing results of searching
   private RecyclerBindingAdapter<MovieDTO> adapter; // adapter for recycler view

   // for pagination logic
   private int currentPage = 1;
   private int nextPage = 1;


   public MainActivityViewModel(MainActivity activity) {
       super(activity);
       this.activity = activity;

       // init model for getting data
       moviesModel = new MoviesModel();

       initList();
   }

   private void initList() {
       adapter = getAdapter();

       // configuring recyclerConfiguration which is bound with recycler view
       recyclerConfiguration.setLayoutManager(new LinearLayoutManager(activity));
       recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
       recyclerConfiguration.setAdapter(adapter);
   }

   private RecyclerBindingAdapter<MovieDTO> getAdapter() {

       int variableId = BR.item; // variable which defined in layout file for movie item

       RecyclerBindingAdapter<MovieDTO> moviesAdapter = new RecyclerBindingAdapter<>(DEVICE_ITEM_LAYOUT, variableId, moviesItems);

       // setting click listener for movie item
       moviesAdapter.setOnItemClickListener(new RecyclerBindingAdapter.OnItemClickListener<MovieDTO>() {
           @Override
           public void onItemClick(int position, MovieDTO item) {
               openMovieDetails(item);
           }
       });

       // passing listener for pagination with atTheEndOfList() method which will invoke after user scroll all list to bottom
       moviesAdapter.setPaginationListener(new RecyclerBindingAdapter.PaginationListener<MovieDTO>() {
           @Override
           public void atTheEndOfList(int position, MovieDTO item) {
               loadNextPage();
           }
       });

       return moviesAdapter;
   }


   @Override
   public void onFindMovieClick(View view) {
       nextPage = 1;
       currentPage = 1;
       searchedQuery = enteredQuery.get();
       findMovie(searchedQuery);
       activity.hideSoftKeyboard(); // hide keyboard to enable displaying of all list with results
   }

   @Override
   public void findMovie(String query) {
       // checking if new page with results exists
       if (currentPage != 1 && nextPage == currentPage) {
           Timber.i("All pages are loaded");
           return;
       }

       Timber.d("Starting loading movies for query: " + query);

       if (nextPage == 1) {
           isLoading.set(true); // show main progress bar
       } else {
           isLoadingNextPage.set(true); // show progress bar for displaying loading of new page
       }

       String apiKey = activity.getResources().getString(R.string.the_movie_db_api_key); // the api key for TheMovieDB API

       // making request with passing necessary parameters and subscribing to listen the results of request
       moviesModel.searchMovieByName(query, nextPage, apiKey).subscribe(new Subscriber<SearchMovieListResponseDTO>() {
           @Override
           public void onCompleted() {
               Timber.i("onCompleted");
               isLoading.set(false);
               isLoadingNextPage.set(false);
           }

           @Override
           public void onError(Throwable e) {
               Timber.e("onError: " + e.getLocalizedMessage());
               Toast.makeText(activity, "onError:" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
               isLoading.set(false);
               isLoadingNextPage.set(false);
           }

           @Override
           public void onNext(SearchMovieListResponseDTO searchMovieListResponseDTO) {
               Timber.i("onNext");
               isLoading.set(false);
               isLoadingNextPage.set(false);
               if (searchMovieListResponseDTO != null) {

                   // if we load first page it means search word was changed, so we need to remove previous results
                   if (nextPage == 1) {
                       moviesItems.clear();
                   }
                   moviesItems.addAll(searchMovieListResponseDTO.getResults());
                   adapter.notifyDataSetChanged();

                   currentPage = Integer.parseInt(searchMovieListResponseDTO.getPage());
                   if (nextPage < searchMovieListResponseDTO.getTotalPages()) {
                       nextPage++;
                   }
               }
           }
       });
   }
  
   @Override
   public void loadNextPage() {
       findMovie(searchedQuery);
   }

   public ArrayList<MovieDTO> getMoviesItems() {
       return moviesItems;
   }

   public ObservableBoolean getIsLoadingNextPage() {
       return isLoadingNextPage;
   }

   @Override
   public void openMovieDetails(MovieDTO movieDTO) {
       Intent intent = new Intent(activity, MovieDetailsActivity.class);
       intent.putExtra(MovieDetailsActivity.MOVIE_OBJECT, movieDTO);
       activity.startActivity(intent);
   }
}
```


**ActivityViewModel (Base class)**

All view models class should extend base **`ActivityViewModel<T>`** class with generic of **`AppCompatActivity`** type. 

```java
public abstract class ActivityViewModel<A extends AppCompatActivity>
       extends BaseObservable {

   protected A activity;

   public ActivityViewModel(A activity) {
       this.activity = activity;
   }

   public A getActivity() {
       return activity;
   }

   public void finish() {
       activity.finish();
   }

   /**
    * Add Activity lifecycle methods here
    */
   }
```


###**Activity class**

**`MainActivity.java`** class is incredibly clear and without a lot of lines of code and it is really nicely. 

```java
public class MainActivity extends BindingActivity<ActivityMainBinding, MainActivityViewModel> {

   private MainActivityViewModel mainActivityViewModel;

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }

   @Override
   public MainActivityViewModel onCreate() {
       mainActivityViewModel = new MainActivityViewModel(this);
       return mainActivityViewModel;
   }

   // method returns variable which defined in xml file 
   @Override
   public int getVariable() {
       return BR.viewModel;
   }

   @Override
   public int getLayoutId() {
       return R.layout.activity_main;
   }
}
```
This class override two methods **`getVariable()`** and **`getLayoutId()`**  from **`BindingActivity.java`** which is using by Data Binding Library for binding xml and view model class presentation. 

### **Base class for Activities with Data Binding implementation**

All activities which is using Data Binding extends BindingActivity class with two generic: 

 * Class which extends **`ViewDataBinding`** class from Data Binding Library. To generate this class it is necessary to add root **`<layout>`** attribute to activity xml file and after that click on Build -> Make Project. After that Data Binding library will generate class which name is based on xml file name converted to Pascal case plus **`Binding`** suffix. (For example xml file **`test_layout.xml`** will be converted to class with name **`TestLayoutBinding`**);

* View model class which extends base **`ActivityViewModel.java`** class;



**BindingActivity.java**

```java
public abstract class BindingActivity<B extends ViewDataBinding, VM extends ActivityViewModel>
       extends BaseActivity {

   private B binding;
   private VM viewModel;

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       bind();
   }

   public void bind() {
       binding = DataBindingUtil.setContentView(this, getLayoutId());
       this.viewModel = viewModel == null ? onCreate() : viewModel;
       binding.setVariable(getVariable(), viewModel);  
       binding.executePendingBindings();
   }

   public void resetViewModel() {
       viewModel = null;
       viewModel = onCreate();
       getBinding().setVariable(getVariable(), viewModel);
   }

   public B getBinding() {
       return binding;
   }

   public abstract VM onCreate();

   public VM getViewModel() {
       return viewModel;
   }

   /**
    * Override for set binding variable
    *
    * @return variable id
    */
   public abstract
   @IdRes
   int getVariable();

   /**
    * Override for set layout resource
    *
    * @return layout resource id
    */
   public abstract
   @LayoutRes
   int getLayoutId();
}
```

**`bind()`** - is main method where logic for binding activity happens. Methods **`getVariable()`** and **`getLayoutId()`** are used for binding and implemented in Activity class which extends **`BindingActivity.java`**. 


###**Base class for all Activities**

As can be noticed above **`BindingActivity.java`** class extends one more base class: **`BaseActivity.java`**. This class consist logic which can be useful for all  activities of the project. Here can be placed logic for showing Toasts, custom Dialogs, checking network state, handling closing the app by double click on back icon and other. 

```
public class BaseActivity extends AppCompatActivity {

   private static final int TAP_BACK_TIME_INTERVAL = 1600;
   private boolean doubleBackToExitPressedOnce = false;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }

   public void hideSoftKeyboard() {
       View view = this.getCurrentFocus();
       if (view != null) {
           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
       }
   }

   @Override
   public void onBackPressed() {
       navigateBack();
   }

   private void navigateBack() {
       FragmentManager fragmentManager = this.getSupportFragmentManager();
       if (fragmentManager.getBackStackEntryCount() > 0) {
           Timber.i("Popping backstack");

           FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
           String fragmentName = backEntry.getName();
           fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
       } else {
           tryExitActivity();
       }
   }

   private void tryExitActivity() {
       Timber.i("Nothing on backstack, calling finish");

       this.hideSoftKeyboard();

       if (this.isTaskRoot()) {
           if (doubleBackToExitPressedOnce) {
               finish();
               return;
           }

           doubleBackToExitPressedOnce = true;
           showToast(getString(R.string.tap_twice_to_close_the_app));
           new Handler().postDelayed(new Runnable() {

               @Override
               public void run() {
                   doubleBackToExitPressedOnce = false;
               }

           }, TAP_BACK_TIME_INTERVAL);
       } else {
           finish();
       }
   }

   public void showToast(String message) {
       Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }
}
```


### **Custom Conversions and Binding Adapters**

**Bidning Adapters** allows to define custom attributes for binding to make some common behaviors of views defined in one place.

For example, the below code allows to load image from network and update image view when picture will be finished. It requires just to bind url of image from view model with **`ImageView`** in xml file by adding the following line:  

```java 
android:src="@{variableName.imageUrl}"
```

For making such logic it is necessary to add public static method with special   **`@BindingAdapter`** annotation as below: 

```java
public final class BindingAdapters {

private BindingAdapters() {
   throw new AssertionError(); // blocking creation of instance 
}

@BindingAdapter("android:src")
public static void loadImage(ImageView view, String url) {
// loading image with third-party library or by own custom utility
   Glide.with(view.getContext())
           .load(url)
           .placeholder(R.drawable.ic_default_movie_thumbnail)
           .into(view);
    }

}
```

The other powerful thing is **Data Binding Conversions**. It allows to convert one type of parameter which added within bound view to another type which is required by view.

One of the most popular and useful custom conversion is converting **boolean** value to **visibility** of view. It helps to bind visibility of any view with boolean value from data class (or view model). As a result this **boolean** value will be a trigger for showing or hiding some view.
For making custom conversion it is necessary to use  **`@BindingConversion`** annotation. Defined method should be **public static**.

```java
public final class BindingConversions {
   private BindingConversions() {
       throw new AssertionError(); // blocking creation of instance 
   }

   @BindingConversion
   public static int convertBooleanToVisibility(boolean visible) {
       return visible ? View.VISIBLE : View.GONE;
   }

}
```

##How to deal with lists

###Xml file 

As can be noticed in the project for making bound recycler view it requires to add one line of code into xml file and pass a reference to a **`recyclerConfiguration`** object from **`viewModel`** variable (which is defined inside **`<data></data>`**). **`recyclerConfiguration`** is an instance of custom base class **`RecyclerConfiguration.java`** which store settings for **`RecyclerView`**. 

The attribute **`app:configuration`** is a custom binding adapter. 

```xml
  <android.support.v7.widget.RecyclerView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:visibility="@{!viewModel.isLoading}"
                app:configuration="@{viewModel.recyclerConfiguration}" />
```

###View model class

In view model class should be defined object with type of **`RecyclerConfiguration.class`** and public access modifier. Also it is possible to use private access but in this case it is necessary to define getter method for it.

Inside **`initList()`** method all logic for initializing and configuring recycler's behaviours is implemented.


```java
public class MainActivityViewModel extends ActivityViewModel<MainActivity> implements MainActivityContract {

  /* Some other variables (see code on GitHub) */
  
    // id of list item
    private final int DEVICE_ITEM_LAYOUT = R.layout.item_movie_preview;  
    
    // for configuring recycler view
    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration(); 
    
    // collection for storing results of searching
    private ArrayList<MovieDTO> moviesItems = new ArrayList<>();     
    
    // custom binding adapter for recycler view
    private RecyclerBindingAdapter<MovieDTO> adapter;
    
    public MainActivityViewModel(MainActivity activity) {
        super(activity);
        initList();
    }

    private void initList() {
        adapter = getAdapter();

        // configuring recyclerConfiguration which is binded with recycler view
        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(activity));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

	/* Some other methods (see full code on GitHub) */
}
```




###RecyclerConfiguration class

**`RecyclerConfiguration.java`** holds configurations for RecyclerView. It has basic properties such as LayoutManager, ItemAnimator and adapter for recycler.
As can be noticed it extends **`BaseObservable.java`** class for refreshing bound parameters when they changed via setter methods.  

Also it is a good practice to use this class as a parent for other classes with custom properties for some custom Recycler Views.

```java
public class RecyclerConfiguration extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;
   
    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }


    @BindingAdapter("app:configuration")
    public static void configureRecyclerView(RecyclerView recyclerView, RecyclerConfiguration configuration) {
        recyclerView.setLayoutManager(configuration.getLayoutManager());
        recyclerView.setItemAnimator(configuration.getItemAnimator());
        if (configuration.onScrollListener!=null) {
            recyclerView.addOnScrollListener(configuration.getOnScrollListener());
        }
        recyclerView.setAdapter(configuration.getAdapter());
    }
}
```

###RecyclerBindingAdapter class

It is possible to bind items of recycler list with data (view model) class by using special adapter for recycler. This class has list with generic type for storing data item. It allows to reuse this custom recycler adapter for different lists which holds different items. 

The logic for binding item view with data item happens inside inner class **`BindingHolder`** while creating an instance of this class by using **`DataBindingUtil`** which is inside Data Binding Library.  

Also there are two listeners for clicking on item and for pagination which are very easy to use (see **`MainActivityViewModel.java`** in the project). 

```java
public class RecyclerBindingAdapter<T>
        extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> {

    private int holderLayout, variableId;
    private AbstractList<T> items = new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;
    private PaginationListener<T> paginationListener;

    public RecyclerBindingAdapter(int holderLayout, int variableId, AbstractList<T> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    @Override
    public RecyclerBindingAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerBindingAdapter.BindingHolder holder, final int position) {
        final T item = items.get(position);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, item);
            }
        });
        // checking for last item (pagination)
        if (paginationListener != null && position == items.size() - 1) {
            paginationListener.atTheEndOfList(position, item);
        }
        holder.getBinding().setVariable(variableId, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

	/**
	*    Define getters and setters here
	**/

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item);
    }

    public interface PaginationListener<T> {
        void atTheEndOfList(int position, T item);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
```





## **Instead of Conclusion**

The Data Binding Library + MVVM is a pretty cool combination for making code more clear and avoiding boilerplates such as **`findViewById()`** for initializing and updating views and handling their states. As a result it reduce time for development especially when there are some practic skills and knowledges of using Data Binding. 


## **Useful links:**

1. [Overview of The Movie DB](https://www.themoviedb.org/about)

	
2. [Official Data Binding Library Documentation](https://developer.android.com/topic/libraries/data-binding/index.html#build_environment)
 	
3. [An excellent demo project on Github](https://github.com/stfalcon-studio/DataBindingExample) 	
 	 
4. [A good tutorial with examples](http://www.journaldev.com/11950/android-data-binding-advanced-concepts)


## License
```
Copyright © 2017 SteelKiwi, http://steelkiwi.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

