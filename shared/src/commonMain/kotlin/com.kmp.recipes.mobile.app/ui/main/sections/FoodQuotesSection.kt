package com.kmp.recipes.mobile.app.ui.main.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kmp.recipes.mobile.app.ui.Dimens
import com.kmp.recipes.mobile.app.ui.common.ColumnX
import com.kmp.recipes.mobile.app.data.model.Quote
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodQuotesSection(quotes: List<Quote>) {
    ColumnX(primaryTitle = "Food Quotes") {
        val pageCount = quotes.size
        val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0F) { pageCount }
        HorizontalPager(
            state = pagerState,
            pageSpacing = Dimens.smallSpacing
        ) { page ->
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(Dimens.cornerSize),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                QuotesContent(quotes[page])
            }
        }
        PagerIndicatorsRow(pageCount, pagerState)
    }
}

@Composable
fun QuotesContent(quote: Quote) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(Dimens.defaultSpacing),
            horizontalArrangement = Arrangement.spacedBy(Dimens.smallSpacing),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(fraction = 0.7f),
                text = quote.quote,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.size(width = 100.dp, height = 100.dp),
                painter = painterResource(SharedRes.images.ic_quote),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing,
                bottom = Dimens.defaultSpacing
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)
        ) {
            Spacer(
                modifier = Modifier.height(2.dp).width(20.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            )
            Text(
                modifier = Modifier.width(200.dp),
                text = quote.author,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 2
            )
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicatorsRow(
    pageCount: Int,
    pagerState: PagerState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) {
            val color =
                if (it == pagerState.currentPage) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            Box(
                Modifier.size(Dimens.smallSpacing).background(
                    color = color,
                    shape = CircleShape
                )
            )
            Spacer(Modifier.width(Dimens.smallSpacing))
        }
    }
}
