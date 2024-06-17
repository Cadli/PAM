package pl.edu.uwr.pum.amigurumitime.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    loggedUser: String,
    title: String,
    content: @Composable (Modifier) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(14.dp), fontSize = 35.sp)

                Divider(modifier = Modifier.padding(top=15.dp))

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Strona główna", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Profil", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Szukaj", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Ulubione", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Dodaj wzór", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                Spacer(modifier = Modifier.height(30.dp))
                NavigationDrawerItem(
                    label = {
                        Row(){
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(text = "  Wyloguj", fontSize = 30.sp)
                        }
                    },
                    selected = false,
                    onClick = {  }
                )
            }
        },
    ) {


        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(1)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ""
                            )
                        }
                    },

                    scrollBehavior = scrollBehavior,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(5.dp)
                )
            }

            ) { innerPadding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                content(Modifier.fillMaxWidth())
            }
        }
    }
}