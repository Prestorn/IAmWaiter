package com.example.iamwaiter.model

import com.example.iamwaiter.model.entities.Category
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.entities.DishInCategory
import com.example.iamwaiter.model.entities.DishInOrder
import com.example.iamwaiter.model.entities.DishStatus
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.OrderStatus
import com.example.iamwaiter.model.entities.Table
import com.example.iamwaiter.model.entities.TableStatus
import com.example.iamwaiter.model.entities.User

class Data {
    companion object{
        val users = listOf<User>(
            User(1, "Александр", "Юрков", "test", "test"),
            User(2, "Дмитрий", "Сергеев", "user2", "user2")
        )

        val tableStatuses = listOf<TableStatus>(
            TableStatus(1, "Свободен"),
            TableStatus(2, "Занят"),
            TableStatus(3, "Забронирован")
        )

        val tables = listOf<Table>(
            Table(1, 2, 3),
            Table(2, 1, 0),
            Table(3, 1, 0),
            Table(4, 1, 0),
            Table(5, 2, 1),
            Table(6, 1, 0),
            Table(7, 1, 0),
            Table(8, 1, 0),
            Table(9, 1, 0),
            Table(10, 2, 2),
            Table(11, 1, 0)
        )

        val orderStatuses = listOf<OrderStatus>(
            OrderStatus(1,"Активен"),
            OrderStatus(2,"Завершён"),
            OrderStatus(3,"Отменён")
        )
        val orders = listOf<Order>(
            Order(1, 1, 1, 1, 1160, 3),
            Order(2, 1, 1, 5, 820, 1),
            Order(3, 1, 1, 10, 820, 2)
        )

        val dishesInOrder = listOf<DishInOrder>(
            DishInOrder(0, 1, 1, 1, 2),
            DishInOrder(0, 31, 1, 2, 1),
            DishInOrder(0, 9, 2, 3, 1),
            DishInOrder(0, 13, 2, 3, 1),
            DishInOrder(0, 11, 3, 4, 1),
            DishInOrder(0, 15, 3, 4, 1),
            DishInOrder(0, 44, 3, 4, 1)
        )

        val dishStatuses = listOf<DishStatus>(
            DishStatus(1, "Заказано"),
            DishStatus(2, "Готовится"),
            DishStatus(3, "Готово к подаче"),
            DishStatus(4, "Подано"),
            DishStatus(5, "Отменено")
        )

        val categories = listOf<Category>(
            Category(1, "Сезонное меню"),
            Category(2, "Завтрак"),
            Category(3, "Первое"),
            Category(4, "Второе"),
            Category(5, "Салаты"),
            Category(6, "Десерты"),
            Category(7, "Кофе и чай"),
            Category(8, "Холодные напитки")
        )

        val dishes = listOf<Dish>(
            Dish(1, "Пшённая каша с рикотой и трюфелем", "Пшено, молоко, пармезан, рикотта, шампиньоны, трюфель, сливочное масло, соль, сахар, микрозелень", 370, 210),
            Dish(2, "Завтрак с индейкой", "Индейка, авокадо, шпинат, яйцо отварное, томаты черри, смесь круп, семена тыквы, соус горчично-медовый (горчица, чеснок, мед, соус соевый и ворчестер соус)", 490, 180),
            Dish(3, "Завтрак с креветками", "Креветки тигровые, авокадо, шпинат, яйцо отварное, томаты черри, смесь круп, семена тыквы, соус горчично-медовый (горчица, чеснок, мед, соус соевый и ворчестер соус)", 570, 180),
            Dish(4, "Завтрак с лососем", "Лосось слабосоленый, авокадо, шпинат, яйцо отварное, томаты черри, смесь круп, семена тыквы, соус горчично-медовый (горчица, чеснок, мед, соус соевый и ворчестер соус)", 590, 180),
            Dish(5, "Драники картофельные с говяжьими колбасками", "Колбаски из говядины, хашбраун, сметана, микрозелень", 370, 190),
            Dish(6, "Омлет летний", "Авокадо, шпинат, редис, брынза, помидоры, яйцо, сливки, песто", 350, 210),
            Dish(7, "Рисовая каша с арахисовой халвой на кокосовой основе", "Рис, кокосовая основа, соус халва (халва арахисовая, сливки, кокосовая основа), соль, сахар, мята", 270, 250),
            Dish(8, "Фирменные сырники", "Творог, сахар, яйцо, мука. Подаются с сметаной и вишнёвым вареньем", 290, 200),
            Dish(9, "Крем-суп с шампиньонами", "Шампиньоны, грибной бульон, сливки, картофель, лук репчатый, гренки пшеничные", 370, 200),
            Dish(10, "Том ям с креветками", "Креветки тигровые, кальмар, шампиньоны, паста том ям, кокосовая основа, соевый соус, лимонник, корень имбиря, чеснок, рис басмати, перец чили, масло чили, кинза, лайм", 640, 390),
            Dish(11, "Борщ с говядиной", "Говядина, свекла, капуста, картофель, морковь, томатная паста, лук репчатый, сметана, укроп", 360, 250),
            Dish(12, "Куриный суп", "Куриный бульон, куриная грудка, вермишель, укроп", 290, 250),
            Dish(13, "Паста \"Качо Э Пепе\"", "Соус перечный (перец, сливки, уксус, чеснок), пармезан, перец черный, спагетти, базилик", 450, 240),
            Dish(14, "Паста \"Помодоро\"", "Соус томатный (помидоры, лук репчатый, чеснок), томаты черри, пармезан, песто, базилик, пенне", 470, 270),
            Dish(15, "Куриные котлеты со сливочным соусом", "Куриные котлеты (куриное бедро, лук репчатый, яйцо, петрушка), томаты черри, соус сливочный, песто, петрушка", 370, 130),
            Dish(16, "Стейк из тунца", "Филе тунца, кабачки, томаты черри, соус чимичури (лук репчатый, перец болгарский, кинза, сельдерей, чеснок, паприка, перец), лимон, микрозелень", 710, 245),
            Dish(17, "Паста \"Альфредо\" с курицей", "Куриная грудка запеченная, соус сливочный, пармезан, паста пенне", 470, 230),
            Dish(18, "Паста \"Карбонара\"", "Бекон жареный из свиной грудинки, сливочный соус, крем из шампиньонов с трюфелем, пармезан, спагетти", 470, 210),
            Dish(19, "Бефстроганов с картофельным пюре", "Говядина, лук, грибы, сметана, сливки, огурцы маринованные, картофельное пюре", 510, 220),
            Dish(20, "Пельмени с говядиной и зеленью", "Фарш из свинины и говядины, репчатый лук, тесто из пшеничной муки, укроп, бульон по желанию", 390, 180),
            Dish(21, "Паста с морепродуктами", "Тигровые креветки, кальмары, соус путтанеска (томаты, каперсы, оливки, анчоусы), базилик, спагетти пармезан", 610, 290),
            Dish(22, "Салат \"Пять зелёных овощей\"", "Авокадо, брокколи, цукини, фасоль стручковая, перец болгарский, рукола, шпинат, соус горчично-медовый", 470, 230),
            Dish(23, "Салат с грушей, свежим фенхелем и сыром дорблю", "Фенхель, груша, клубника, дорблю, айсберг, рукола, соус апельсиновый (растительное масло, винный уксус, апельсиновый сок)", 490, 150),
            Dish(24, "\"Цезарь\" с курицей", "Куриная грудка запеченная, айсберг, соус цезарь на основе анчоусов, крутоны с паприкой, томаты черри, пармезан", 470, 210),
            Dish(25, "Салат \"Оливье\"", "Куриная грудка, яйцо отварное, картофель отварной, огурцы, морковь, горошек консервированный, яблоко, лук зеленый, майонез", 330, 170),
            Dish(26, "Салат \"Греческий\"", "Огурцы, перец болгарский, айсберг, томаты черри, маслины, оливки, брынза, масло оливковое, орегано", 390, 230),
            Dish(27, "\"Цезарь\" с креветками", "Креветки тигровые, айсберг, соус цезарь на основе анчоусов, крутоны с паприкой, томаты черри, пармезан", 590, 230),
            Dish(28, "Десерт \"Пломбир\"", "Нежный десерт с клубнично-сливочными нотками, малиновым пюре, покрытый глазурью из белого шоколада. Клубника, сливки, белый шоколад, малиновое пюре, молоко, лимон", 390, 75),
            Dish(29, "Пирожное \"Тутти-фрутти\"", "Освежающий десерт из свежих фруктов на бисквитном корже. Киви, ананас, банан, персик, пюре из маракуйя, апельсин, лимон, мятный сироп, мука, яйцо, сливки, какао, белый шоколад", 420, 90),
            Dish(30, "\"Птичье молоко\"", "Легкий торт суфле в шоколадной глазури. Сгущённое молоко, шоколад, ванилин, мука сахар, масло сливочное, яйца", 350, 100),
            Dish(31, "Торт \"Шведский\"", "Торт из миндальных коржей с прослойками из ванильного заварного крема, покрыт молочным шоколадом и карамелью. Сливки, сахар,  молоко, мука, миндаль,  шоколад молочный, ванильная паста", 420, 80),
            Dish(32, "Вишнёвый клафути", "Сочное подрумяненное тесто с нежным ванильно-сливочным кремом и вишней. Сливки, мука, яйцо, вишня, миндальная мука, масло сливочное, молоко, сахарная пудра, фисташки", 420, 120),
            Dish(33, "Капучино", "Сбалансированный напиток на основе яркого эспрессо и вспененного молока. Эспрессо, вспененное молоко, молочная пена", 230, 200),
            Dish(34, "Американо", "Классический напиток на основе эспрессо", 230, 300),
            Dish(35, "Флет Уайт", "Кофейно-молочный напиток, придуманный в Австралии, с ярко выраженным вкусом кофе. Эспрессо, вспененное молоко, молочная пена", 270, 250),
            Dish(36, "Латте", "Идеальное сочетание молока, классического эспрессо и молочной пены. Молочная пена, вспененное молоко, эспрессо", 270, 300),
            Dish(37, "Раф классический", "Ароматный сливочный кофе. Вспененные сливки, эспрессо, ванильный сахар", 290, 300),
            Dish(38, "Чай листовой \"Ассам\"", "Классический листовой чай", 270, 500),
            Dish(39, "Чай листовой \"Эрл грей\"", "Классический листовой чай", 270, 500),
            Dish(40, "Чай листовой \"Сенча\"", "Классический листовой чай", 270, 500),
            Dish(41, "Чай листовой \"Жасмин\"", "Классический листовой чай", 270, 500),
            Dish(42, "Чай листовой \"Молочный улун\"", "Классический листовой чай", 270, 500),
            Dish(43, "Чай листовой \"Гречишный\"", "Классический листовой чай", 270, 500),
            Dish(44, "Клюквенный морс", "Домашний клюквенный морс", 90, 220),
            Dish(45, "Эвервесс Кола", "Бутылочный газированный напиток", 190, 250),
            Dish(46, "Эвервесс Кола без сахара", "Бутылочный газированный напиток", 190, 250),
            Dish(47, "Эвервесс Апельсин", "Бутылочный газированный напиток", 190, 250),
            Dish(48, "Эвервесс Лемон-Лайм", "Бутылочный газированный напиток", 190, 250),
            Dish(49, "Вода Аква Минерале газированая", "Газированая вода", 170, 500),
            Dish(50, "Вода Аква Минерале негазированая", "Негазированая вода", 170, 500)
            )

        val dishesInCategories = listOf<DishInCategory>(
            DishInCategory(0, 1, 2),
            DishInCategory(0, 2, 2),
            DishInCategory(0, 3, 2),
            DishInCategory(0, 4, 2),
            DishInCategory(0, 5, 2),
            DishInCategory(0, 6, 2),
            DishInCategory(0, 7, 2),
            DishInCategory(0, 8, 2),
            DishInCategory(0, 9, 3),
            DishInCategory(0, 10, 3),
            DishInCategory(0, 11, 3),
            DishInCategory(0, 12, 3),
            DishInCategory(0, 13, 4),
            DishInCategory(0, 14, 4),
            DishInCategory(0, 15, 4),
            DishInCategory(0, 16, 4),
            DishInCategory(0, 17, 4),
            DishInCategory(0, 18, 4),
            DishInCategory(0, 19, 4),
            DishInCategory(0, 20, 4),
            DishInCategory(0, 21, 4),
            DishInCategory(0, 22, 5),
            DishInCategory(0, 23, 5),
            DishInCategory(0, 24, 5),
            DishInCategory(0, 25, 5),
            DishInCategory(0, 26, 5),
            DishInCategory(0, 27, 5),
            DishInCategory(0, 28, 6),
            DishInCategory(0, 29, 6),
            DishInCategory(0, 30, 6),
            DishInCategory(0, 31, 6),
            DishInCategory(0, 32, 6),
            DishInCategory(0, 33, 7),
            DishInCategory(0, 34, 7),
            DishInCategory(0, 35, 7),
            DishInCategory(0, 36, 7),
            DishInCategory(0, 37, 7),
            DishInCategory(0, 38, 7),
            DishInCategory(0, 39, 7),
            DishInCategory(0, 40, 7),
            DishInCategory(0, 41, 7),
            DishInCategory(0, 42, 7),
            DishInCategory(0, 43, 7),
            DishInCategory(0, 44, 8),
            DishInCategory(0, 45, 8),
            DishInCategory(0, 46, 8),
            DishInCategory(0, 47, 8),
            DishInCategory(0, 48, 8),
            DishInCategory(0, 49, 8),
            DishInCategory(0, 50, 8),
            DishInCategory(0, 6, 1),
            DishInCategory(0, 7, 1),
            DishInCategory(0, 13, 1),
            DishInCategory(0, 22, 1),
            DishInCategory(0, 28, 1)
        )
    }
}