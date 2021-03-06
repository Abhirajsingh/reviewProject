import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    location: {embedded: 'always'},
    skills : {embedded: 'always'},
    department: {embedded: 'always'},
    project: {embedded: 'always'},
  }
});

