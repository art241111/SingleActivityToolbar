package ru.art241111.singleactivitytoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Set toolbar
        setSupportActionBar(toolbar);

        // Reference to the displayed fragment
        // Here my_nav_host_fragment is an ID of the fragment tag in your
        // content_main.xml with android:name="androidx.navigation.fragment.NavHostFragment"
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Get the navigation controller
        // From documentation:
        // NavController manages app navigation within a {@link NavHost}
        val navController = host.navController

        // Installing NavController on the settings button
        setupBottomNavMenu(navController)
    }

    /**
     * Initialize the contents of the Activity's standard options menu
     * This is only called once, the first time the options menu is displayed.
     * @param menu - The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return
     * false it will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_info_menu, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * You can use this method for any items for
     * Derived classes should call through to the base class for it to perform
     * the default menu handling.
     * @param item - The menu item that was selected. This value cannot be null.
     * @return The menu item that was selected. This value cannot be null.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    /**
     * Installing NavController on the settings button
     * @param navController - NavController associated with navigation
     */
    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<NavigationView>(R.id.nav_view)
        bottomNav?.setupWithNavController(navController)
    }

}