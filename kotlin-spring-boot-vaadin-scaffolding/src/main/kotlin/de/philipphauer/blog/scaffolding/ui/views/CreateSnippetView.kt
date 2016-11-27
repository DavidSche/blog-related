package de.philipphauer.blog.scaffolding.ui.views

import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.data.util.BeanItem
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.FontAwesome
import com.vaadin.server.Sizeable
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import de.philipphauer.blog.scaffolding.db.SnippetRepository
import de.philipphauer.blog.scaffolding.ui.Labels
import de.philipphauer.blog.scaffolding.ui.PropertyIds
import de.philipphauer.blog.scaffolding.ui.SnippetCreationItem
import javax.annotation.PostConstruct


@SpringView(name = CreateSnippetView.VIEW_NAME)
class CreateSnippetView(val repo: SnippetRepository) : VerticalLayout(), View {

    companion object{
        const val VIEW_NAME = "create"
        const val LABEL = "Create Snippet"
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
    }

    @PostConstruct
    internal fun init() {
        val form = CreateSnippetForm().apply {
            createButton.addClickListener { createSnippet() }
        }
        val emptyItem = BeanItem<SnippetCreationItem>(SnippetCreationItem())
        val fieldGroup = FieldGroup(emptyItem).apply {
            buildAndBindMemberFields(form)
        }
        setSizeFull()
        addComponent(form)
        setExpandRatio(form, 1f)
    }

    private fun createSnippet() {
        Notification.show("Snippet creation not implemented.")
    }
}

class CreateSnippetForm : FormLayout() {

    @PropertyId(PropertyIds.CODE)
    val code = TextArea(Labels.CODE).apply {
        nullRepresentation = ""
        setWidth(100f, Sizeable.Unit.PERCENTAGE)
        isRequired = true
        addStyleName("monospace")
    }

    @PropertyId(PropertyIds.AUTHOR)
    val author = TextField(Labels.AUTHOR).apply {
        nullRepresentation = ""
        setWidth(100f, Sizeable.Unit.PERCENTAGE)
    }

    val createButton = Button("Create Snippet", FontAwesome.CODE)

    init {
        setSizeFull()
        isSpacing = true
        addComponents(code, author, createButton)
    }
}